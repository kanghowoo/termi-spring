package termi.termispring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import termi.termispring.domain.Message;
import termi.termispring.dto.MessageForm;
import termi.termispring.repository.MessageRepository;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void sendMessage(Message message) {
        messageRepository.send(message);
    }

    @Override
    public List<MessageForm> getMessages(Long id) {
        return messageRepository.getMessages(id);
    }

    @Override
    public MessageForm getMessageById(Long id) {
        return messageRepository.getMessageById(id);
    }

}
