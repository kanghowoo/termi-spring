package termi.termispring.service;

import termi.termispring.domain.Message;
import termi.termispring.dto.MessageResponse;

import java.util.List;

public interface MessageService {

    void sendMessage(Message message);
    List<Message> getMessagesByUserId(Long id);
    Message getMessageByMessageId(Long id);
}
