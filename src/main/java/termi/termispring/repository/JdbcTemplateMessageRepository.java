package termi.termispring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import termi.termispring.domain.Message;
import termi.termispring.dto.MessageForm;

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
    public List<MessageForm> getMessages(Long id) {
        return jdbcTemplate.query("select msg_id,msg_receiver_id,msg_sender_id,msg_content,msg_send_time,"+
                "r.user_name as receiverName,s.user_name as senderName " +
                "from message m " +
                "left join user r on m.msg_receiver_id = r.user_id " +
                "left join user s on m.msg_sender_id = s.user_id " +
                "where r.user_id = ?", getMessageMapper(),id);
    }

    @Override
    public MessageForm getMessageById(Long id) {
        return jdbcTemplate.queryForObject("select msg_id,msg_receiver_id,msg_sender_id,msg_content,msg_send_time," +
                "r.user_name as receiverName,s.user_name as senderName " +
                "from message m " +
                "left join user r on m.msg_receiver_id = r.user_id " +
                "left join user s on m.msg_sender_id = s.user_id " +
                "where msg_id = ?",getMessageMapper(),id);
    }

    private RowMapper<MessageForm> getMessageMapper() {
        return (rs, rowNum) -> {

            MessageForm form = new MessageForm();
            form.setId(rs.getLong("msg_id"));
            form.setSenderId(rs.getLong("msg_sender_id"));
            form.setReceiverId(rs.getLong("msg_receiver_id"));
            form.setSenderName(rs.getString("senderName"));
            form.setReceiverName(rs.getString("receiverName"));
            form.setContent(rs.getString("msg_content"));
            form.setSendTime(rs.getTimestamp("msg_send_time"));
            return form;
        };
    }
}
