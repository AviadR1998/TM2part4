package com.example.tm2ex3.messagesJsons;

public class MessageMJson {
    private int id;
    private String created;
    private SenderJSON sender;
    private String content;

    public MessageMJson(int id, String created, SenderJSON sender, String content) {
        this.id = id;
        this.created = created;
        this.sender = sender;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getCreated() {
        return created;
    }

    public int getId() {
        return id;
    }

    public SenderJSON getSender() {
        return sender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSender(SenderJSON sender) {
        this.sender = sender;
    }
}
