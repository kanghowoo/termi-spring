package termi.termispring.domain;

import termi.termispring.dto.MemberResponse;

import java.sql.Timestamp;

public class Message {

    private Long id;
    private MemberResponse sender;
    private MemberResponse receiver;
    private Timestamp sendTime;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MemberResponse getSender() {
        return sender;
    }

    public void setSender(MemberResponse sender) {
        this.sender = sender;
    }

    public MemberResponse getReceiver() {
        return receiver;
    }

    public void setReceiver(MemberResponse receiver) {
        this.receiver = receiver;
    }
}
