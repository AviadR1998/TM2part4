package com.example.tm2ex3.messagesJsons;

import com.example.tm2ex3.UserJSON;

public class PostMessagesJSON {
    private int id;
    private String created;
    private UserJSON sender;
    private String content;

    public PostMessagesJSON(int id, String created, UserJSON sender, String content) {
        this.id = id;
        this.created = created;
        this.sender = sender;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getCreated() {
        return created;
    }

    public String getContent() {
        return content;
    }

    public UserJSON getSender() {
        return sender;
    }

    public void setSender(UserJSON sender) {
        this.sender = sender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
