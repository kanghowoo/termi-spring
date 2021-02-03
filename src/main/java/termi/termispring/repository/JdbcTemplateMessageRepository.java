package termi.termispring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import termi.termispring.domain.Member;
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
    public void sendMessage(Message message) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("message").usingGeneratedKeyColumns("msg_id");
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("msg_sender_id",message.getSender().getId());
        parameters.put("msg_receiver_id",message.getReceiver().getId());
        parameters.put("msg_content", message.getContent());
        parameters.put("msg_send_time", message.getSendTime());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        message.setId(key.longValue());

    }

    @Override
    public List<Message> getMessagesByUserId(Long memberId) {
        return jdbcTemplate.query("select msg_id,msg_receiver_id,msg_sender_id,msg_content,msg_send_time,"+
                "r.mem_name as receiverName,s.mem_name as senderName " +
                "from message m " +
                "left join member r on m.msg_receiver_id = r.mem_id " +
                "left join member s on m.msg_sender_id = s.mem_id " +
                "where r.mem_id = ?", getMessageMapper(),memberId);
    }

    @Override
    public Message getMessageByMessageId(Long messageId) {
        return jdbcTemplate.queryForObject("select msg_id,msg_receiver_id,msg_sender_id,msg_content,msg_send_time," +
                "r.mem_name as receiverName,s.mem_name as senderName " +
                "from message m " +
                "left join member r on m.msg_receiver_id = r.mem_id " +
                "left join member s on m.msg_sender_id = s.mem_id " +
                "where msg_id = ?",getMessageMapper(),messageId);
    }

    private RowMapper<Message> getMessageMapper() {
        return (rs, rowNum) -> {

            Message message = new Message();
            Member sender = new Member();
            Member receiver = new Member();

            message.setId(rs.getLong("msg_id"));
            sender.setId(rs.getLong("msg_sender_id"));
            sender.setName(rs.getString("senderName"));

            receiver.setId(rs.getLong("msg_receiver_id"));
            receiver.setName(rs.getString("receiverName"));

            message.setSender(sender);
            message.setReceiver(receiver);
            message.setSendTime(rs.getTimestamp("msg_send_time"));
            message.setContent(rs.getString("msg_content"));

            return message;
        };
    }
}
