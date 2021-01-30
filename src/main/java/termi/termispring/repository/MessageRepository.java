package termi.termispring.repository;

import termi.termispring.domain.Message;

import java.util.List;

public interface MessageRepository {

    Message send(Message message);
    List<Message> getMessages();
    Message getMessageById(Long id);
}
