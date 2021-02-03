package termi.termispring.mapper;

import org.springframework.stereotype.Component;
import termi.termispring.domain.Member;
import termi.termispring.domain.Message;
import termi.termispring.dto.MemberResponse;
import termi.termispring.dto.MessageCreateRequest;
import termi.termispring.dto.MessageResponse;

@Component
public class MessageMapperImpl implements MessageMapper{
    @Override
    public Message requestToMessage(MessageCreateRequest request) {
        Message message = new Message();
        Member sender = new Member();
        Member receiver = new Member();

        sender.setId(request.getSenderId());
        receiver.setId(request.getReceiverId());

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(request.getContent());

        return message;
    }

    @Override
    public MessageResponse messageToResponse(Message message) {
        MessageResponse response = new MessageResponse();

        MemberResponse sender = new MemberResponse();
        MemberResponse receiver = new MemberResponse();

        sender.setId(message.getSender().getId());
        sender.setName(message.getSender().getName());
        receiver.setId(message.getReceiver().getId());
        receiver.setName(message.getReceiver().getName());

        response.setId(message.getId());
        response.setSender(sender);
        response.setReceiver(receiver);
        response.setSendTime(message.getSendTime());
        response.setContent(message.getContent());

        return response;
    }
}




