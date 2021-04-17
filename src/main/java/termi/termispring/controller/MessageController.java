package termi.termispring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import termi.termispring.domain.Message;
import termi.termispring.dto.MessageCreateRequest;
import termi.termispring.dto.MessageResponse;
import termi.termispring.mapper.MessageMapper;
import termi.termispring.service.MessageService;

import java.util.List;

@RestController
public class MessageController {

    private final MessageService messageService;
    private final MessageMapper messageMapper;

    @Autowired
    public MessageController(MessageService messageService,MessageMapper messageMapper) {
        this.messageService = messageService;
        this.messageMapper = messageMapper;
    }


    @PostMapping(value = "/messages")
    public MessageCreateRequest sendMessage(@RequestHeader("Authorization") String token, @RequestBody MessageCreateRequest request) {
        Message message = messageMapper.requestToMessage(request);
        messageService.sendMessage(message);
        return request;
    }

    @GetMapping(value = "/users/{memberId}/messages")
    public List getMessagesByUserId(@RequestHeader("Authorization") String token, @PathVariable Long memberId) {
        List<Message> messageList = messageService.getMessagesByUserId(memberId);
//        model.addAttribute("message",messageList);
        return messageList;
    }

    @GetMapping(value = "/messages/{messageId}")
    public MessageResponse getMessageByMessageId(@RequestHeader("Authorization") String token, @PathVariable Long messageId) {
        Message message = messageService.getMessageByMessageId(messageId);
        MessageResponse response = messageMapper.messageToResponse(message);
        return response;
    }
}
