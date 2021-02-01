package termi.termispring.mapper;

import org.springframework.stereotype.Component;
import termi.termispring.domain.Message;
import termi.termispring.domain.User;
import termi.termispring.dto.MessageCreateRequest;
import termi.termispring.dto.MessageResponse;

@Component
public class MessageMapperImpl implements MessageMapper{
    @Override
    public Message requestToMessage(MessageCreateRequest request) {
        Message message = new Message();
        User sender = new User();
        User receiver = new User();

        sender.setUser_id(request.getSenderId());
        receiver.setUser_id(request.getReceiverId());

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(request.getContent());

        return message;
    }

    @Override
    public MessageResponse messageToResponse(Message message) {
        MessageResponse response = new MessageResponse();

        User sender = new User();
        User receiver = new User();

        sender.setUser_id(message.getSender().getUser_id());
        sender.setUser_name(message.getSender().getUser_name());
        receiver.setUser_id(message.getReceiver().getUser_id());
        receiver.setUser_name(message.getReceiver().getUser_name());

        response.setId(message.getId());
        response.setSender(sender);
        response.setReceiver(receiver);
        response.setSendTime(message.getSendTime());
        response.setContent(message.getContent());

        return response;
    }
}




