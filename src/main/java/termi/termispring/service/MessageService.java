package termi.termispring.service;

import termi.termispring.domain.Message;
import termi.termispring.dto.MessageForm;

import java.util.List;

public interface MessageService {

    void sendMessage(Message message);
    List<MessageForm> getMessages(Long id);
    MessageForm getMessageById(Long id);
}
