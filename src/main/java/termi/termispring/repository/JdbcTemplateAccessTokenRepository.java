package termi.termispring.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import termi.termispring.domain.AccessToken;
import termi.termispring.util.AccessTokenHelper;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class JdbcTemplateAccessTokenRepository implements AccessTokenRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateAccessTokenRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void createToken(AccessToken accessToken) throws UnsupportedEncodingException {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("access_token").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("token", accessToken.getAccessToken());
        parameters.put("mem_id", accessToken.getMemberId());

        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        accessToken.setId(key.longValue());
    }
}
