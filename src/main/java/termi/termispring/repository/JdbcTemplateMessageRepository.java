package termi.termispring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import termi.termispring.domain.Message;
import termi.termispring.domain.User;

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
        parameters.put("msg_sender_id",message.getSender().getUser_id());
        parameters.put("msg_receiver_id",message.getReceiver().getUser_id());
        parameters.put("msg_content", message.getContent());
        parameters.put("msg_send_time", message.getSendTime());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        message.setId(key.longValue());

        return message;
    }

    @Override
    public List<Message> getMessagesByUserId(Long userId) {
        return jdbcTemplate.query("select msg_id,msg_receiver_id,msg_sender_id,msg_content,msg_send_time,"+
                "r.user_name as receiverName,s.user_name as senderName " +
                "from message m " +
                "left join user r on m.msg_receiver_id = r.user_id " +
                "left join user s on m.msg_sender_id = s.user_id " +
                "where r.user_id = ?", getMessageMapper(),userId);
    }

    @Override
    public Message getMessageByMessageId(Long messageId) {
        return jdbcTemplate.queryForObject("select msg_id,msg_receiver_id,msg_sender_id,msg_content,msg_send_time," +
                "r.user_name as receiverName,s.user_name as senderName " +
                "from message m " +
                "left join user r on m.msg_receiver_id = r.user_id " +
                "left join user s on m.msg_sender_id = s.user_id " +
                "where msg_id = ?",getMessageMapper(),messageId);
    }

    private RowMapper<Message> getMessageMapper() {
        return (rs, rowNum) -> {

            Message message = new Message();
            User sender = new User();
            User receiver = new User();

            message.setId(rs.getLong("msg_id"));
            sender.setUser_id(rs.getLong("msg_sender_id"));
            sender.setUser_name(rs.getString("senderName"));

            receiver.setUser_id(rs.getLong("msg_receiver_id"));
            receiver.setUser_name(rs.getString("receiverName"));

            message.setSender(sender);
            message.setReceiver(receiver);
            message.setSendTime(rs.getTimestamp("msg_send_time"));
            message.setContent(rs.getString("msg_content"));

            return message;
        };
    }
}
