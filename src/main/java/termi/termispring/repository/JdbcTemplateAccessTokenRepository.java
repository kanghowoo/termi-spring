package termi.termispring.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import termi.termispring.domain.AccessToken;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateAccessTokenRepository implements AccessTokenRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateAccessTokenRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void createToken(AccessToken accessToken) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("access_token").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("token", accessToken.getAccessToken());
        parameters.put("mem_id", accessToken.getMemberId());

        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        accessToken.setId(key.longValue());
    }

    @Override
    public void updateToken(AccessToken accessToken) {

        jdbcTemplate.update("update access_token set token = ? where mem_id = ?",
                accessToken.getAccessToken(),accessToken.getMemberId());
    }

    @Override
    public Optional<AccessToken> findAccessTokenByMemberId(Long memberId) {
        List<AccessToken> result = jdbcTemplate.query("select * from access_token where mem_id = ?",
        getAccessTokenMapper(), memberId);
        return result.stream().findAny();

    }


    private RowMapper<AccessToken> getAccessTokenMapper() {

        return (rs, rowNum) -> {
            AccessToken accessToken = new AccessToken();
            accessToken.setId(rs.getLong("id"));
            accessToken.setMemberId(rs.getLong("mem_id"));
            accessToken.setAccessToken(rs.getString("token"));

            return accessToken;
        };
    }

}
