package termi.termispring.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import termi.termispring.domain.Member;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createMember(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("mem_id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("mem_name", member.getName());
        parameters.put("mem_email", member.getEmail());
        parameters.put("mem_password", member.getPassword());
        parameters.put("mem_createdAt", member.getCreatedAt());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        member.setId(key.longValue());
    }

    @Override
    public Optional<Member> findMemberByEmail(String email) {
        List<Member> result = jdbcTemplate.query("select * from member where mem_email = ?", getMemberMapper(), email);
        return result.stream().findAny();
    }

    private RowMapper<Member> getMemberMapper() {

        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("mem_id"));
            member.setName(rs.getString("mem_name"));
            member.setEmail(rs.getString("mem_email"));
            member.setPassword(rs.getString("mem_password"));
            member.setCreatedAt(rs.getTimestamp("mem_createdAt"));
            return member;
        };
    }

}
