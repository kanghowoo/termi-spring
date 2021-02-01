package termi.termispring.repository;

import termi.termispring.domain.Message;
import termi.termispring.dto.MessageResponse;

import java.util.List;

public interface MessageRepository {

    Message send(Message message);
    List<Message> getMessagesByUserId(Long userId);
    Message getMessageByMessageId(Long messageId);
}
