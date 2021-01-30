package termi.termispring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import termi.termispring.domain.Message;
import termi.termispring.dto.MessageForm;
import termi.termispring.service.MessageService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }


    @PostMapping(value = "/message")
    public Message sendMessage(@RequestBody MessageForm form) {
        Message message = new Message();
        message.setSenderId(form.getSenderId());
        message.setReceiverId(form.getReceiverId());
        message.setContent(form.getContent());
        message.setSendTime(Timestamp.valueOf(LocalDateTime.now()));

        messageService.sendMessage(message);
        return message;
    }

    @GetMapping(value = "/messages")
    public List getMessages(Model model) {
        List<Message> messageList = messageService.getMessages();
        model.addAttribute("message",messageList);

        return messageList;
    }

    @GetMapping(value = "/message")
    public Message getMessageById(@RequestParam Long id) {
        Message message = new Message();
        message = messageService.getMessageById(id);
        return message;
    }
}
