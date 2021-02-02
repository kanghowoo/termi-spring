package termi.termispring.repository;

import termi.termispring.domain.Message;
import termi.termispring.dto.MessageResponse;

import java.util.List;

public interface MessageRepository {

    void sendMessage(Message message);
    List<Message> getMessagesByUserId(Long memberId);
    Message getMessageByMessageId(Long messageId);
}
