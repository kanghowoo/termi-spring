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
    public Message sendMessage(@RequestBody MessageCreateRequest request) {
        Message message = messageMapper.requestToMessage(request);
        messageService.sendMessage(message);
        return message;
    }

    @GetMapping(value = "/users/{userId}/messages")
    public List getMessagesByUserId(@PathVariable Long userId) {
        List<Message> messageList = messageService.getMessagesByUserId(userId);
//        model.addAttribute("message",messageList);
        return messageList;
    }

    @GetMapping(value = "/messages/{messageId}")
    public MessageResponse getMessageByMessageId(@PathVariable Long messageId) {
        Message message = messageService.getMessageByMessageId(messageId);
        MessageResponse response = messageMapper.messageToResponse(message);
        return response;
    }
}
