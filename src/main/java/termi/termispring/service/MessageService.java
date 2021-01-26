package termi.termispring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import termi.termispring.domain.Message;
import termi.termispring.repository.MessageRepository;

import java.util.List;

@Transactional
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void send(Message message) {
        messageRepository.send(message);
    }

    public List<Message> findMessages() {
        return messageRepository.findAll();
    }

}
