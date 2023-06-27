package com.example.tm2ex3.messagesJsons;

import com.example.tm2ex3.UserJSON;

import java.util.List;

public class GetAllChatInfo {
    private String id;
    private List<UserJSON> users;
    private List<PostMessagesJSON> messages;

    public GetAllChatInfo(String id, List<UserJSON> users, List<PostMessagesJSON> messages) {
        this.id = id;
        this.users = users;
        this.messages = messages;
    }

    public String getId() {
        return id;
    }

    public List<PostMessagesJSON> getMessages() {
        return messages;
    }

    public List<UserJSON> getUsers() {
        return users;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMessages(List<PostMessagesJSON> messages) {
        this.messages = messages;
    }

    public void setUsers(List<UserJSON> users) {
        this.users = users;
    }
}
