package termi.termispring.repository;

import termi.termispring.domain.Message;
import termi.termispring.dto.MessageForm;

import java.util.List;

public interface MessageRepository {

    Message send(Message message);
    List<MessageForm> getMessages(Long id);
    MessageForm getMessageById(Long id);
}
