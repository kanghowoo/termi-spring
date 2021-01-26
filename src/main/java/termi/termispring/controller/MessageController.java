package termi.termispring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import termi.termispring.domain.Message;
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
//    @ResponseBody
    public Message send(@RequestBody MessageForm form) {
        Message message = new Message();
        message.setMsg_sender(form.getMsg_sender());
        message.setMsg_receiver(form.getMsg_receiver());
        message.setMsg_content(form.getMsg_content());
        message.setMsg_send_time(Timestamp.valueOf(LocalDateTime.now()));

        messageService.send(message);
        return message;
    }

    @GetMapping(value = "/message")
    public List list(Model model) {
        List<Message> messageList = messageService.findMessages();
        model.addAttribute("message",messageList);

        return messageList;
    }

}
