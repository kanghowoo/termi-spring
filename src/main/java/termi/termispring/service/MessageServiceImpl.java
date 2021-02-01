package termi.termispring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import termi.termispring.domain.Message;
import termi.termispring.repository.MessageRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void sendMessage(Message message) {
        message.setSendTime(Timestamp.valueOf(LocalDateTime.now()));
        messageRepository.send(message);
    }

    @Override
    public List<Message> getMessagesByUserId(Long userId) {
        return messageRepository.getMessagesByUserId(userId);
    }

    @Override
    public Message getMessageByMessageId(Long messageId) {
        return messageRepository.getMessageByMessageId(messageId);
    }

}
