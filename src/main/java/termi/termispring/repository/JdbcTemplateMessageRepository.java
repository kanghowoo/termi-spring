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
        parameters.put("msg_sender_id",message.getSenderId());
        parameters.put("msg_receiver_id",message.getReceiverId());
        parameters.put("msg_content", message.getContent());
        parameters.put("msg_send_time", message.getSendTime());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        message.setId(key.longValue());

        return message;
    }

    @Override
    public List<Message> getMessages() {
        return jdbcTemplate.query("select * from message", messageRowMapper());
    }

    @Override
    public Message getMessageById(Long id) {
        return jdbcTemplate.queryForObject("select * from message where msg_receiver_id =?",messageRowMapper(),id);
    }

    private RowMapper<Message> messageRowMapper() {
        return (rs, rowNum) -> {

            Message message = new Message();
            message.setId(rs.getLong("msg_id"));
            message.setSenderId(rs.getLong("msg_sender_id"));
            message.setReceiverId(rs.getLong("msg_receiver_id"));
            message.setContent(rs.getString("msg_content"));
            message.setSendTime(rs.getTimestamp("msg_send_time"));
            return message;
        };
    }


}
