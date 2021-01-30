package termi.termispring.service;

import termi.termispring.domain.Message;
import termi.termispring.dto.MessageForm;

import java.util.List;

public interface MessageService {

    void sendMessage(Message message);
    List<Message> getMessages();
    Message getMessageById(Long id);
}
