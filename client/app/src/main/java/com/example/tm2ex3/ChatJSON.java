package com.example.tm2ex3;

public class ChatJSON {
    private String id;
    private UserJSON user;
    private MessagesJSON lastMessage;

    public ChatJSON(String id, UserJSON user, MessagesJSON lastMessage) {
        this.id = id;
        this.user = user;
        this.lastMessage = lastMessage;
    }

    public MessagesJSON getLastMessage() {
        return lastMessage;
    }

    public String getId() {
        return id;
    }

    public UserJSON getUser() {
        return user;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLastMessage(MessagesJSON lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setUser(UserJSON user) {
        this.user = user;
    }
}
