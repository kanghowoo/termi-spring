package termi.termispring.mapper;

import org.springframework.stereotype.Component;
import termi.termispring.domain.Message;
import termi.termispring.dto.MessageCreateRequest;
import termi.termispring.dto.MessageResponse;


public interface MessageMapper {

    Message requestToMessage(MessageCreateRequest request);
    MessageResponse messageToResponse(Message message);

}
