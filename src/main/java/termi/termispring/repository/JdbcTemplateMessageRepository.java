package termi.termispring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import termi.termispring.domain.Message;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplateMessageRepository implements MessageRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateMessageRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Message send(Message message) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("message").usingGeneratedKeyColumns("msg_id");
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("msg_sender",message.getMsg_sender());
        parameters.put("msg_receiver",message.getMsg_receiver());
        parameters.put("msg_content", message.getMsg_content());
        parameters.put("msg_send_time", message.getMsg_send_time());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        message.setMsg_id(key.longValue());

        return message;
    }

    @Override
    public List<Message> findAll() {
        return jdbcTemplate.query("select * from message", messageRowMapper());
    }

    private RowMapper<Message> messageRowMapper() {
        return (rs, rowNum) -> {

            Message message = new Message();
            message.setMsg_id(rs.getLong("msg_id"));
            message.setMsg_sender(rs.getString("msg_sender"));
            message.setMsg_receiver(rs.getString("msg_receiver"));
            message.setMsg_content(rs.getString("msg_content"));
            message.setMsg_send_time(rs.getTimestamp("msg_send_time"));
            return message;
        };
    }
}
