package termi.termispring.domain;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Message {

    private Long msg_id;
    private String msg_sender;
    private String msg_receiver;
    private Timestamp msg_send_time;
    private String msg_content;

    public Long getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(Long msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_sender() {
        return msg_sender;
    }

    public void setMsg_sender(String msg_sender) {
        this.msg_sender = msg_sender;
    }

    public String getMsg_receiver() {
        return msg_receiver;
    }

    public void setMsg_receiver(String msg_receiver) {
        this.msg_receiver = msg_receiver;
    }

    public void setMsg_send_time(Timestamp msg_send_time) {
        this.msg_send_time = msg_send_time;
    }

    public Timestamp getMsg_send_time() {
        return msg_send_time;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }
}
