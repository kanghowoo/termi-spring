package termi.termispring.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class MessageForm {
    private String msg_sender;
    private String msg_receiver;
    private String msg_content;

    public String getMsg_receiver() {
        return msg_receiver;
    }

    public void setMsg_receiver(String msg_receiver) {
        this.msg_receiver = msg_receiver;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public String getMsg_sender() {
        return msg_sender;
    }

    public void setMsg_sender(String msg_sender) {
        this.msg_sender = msg_sender;
    }

}
