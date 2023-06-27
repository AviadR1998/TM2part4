package com.example.tm2ex3;

public class NewChatJSON {
    private String id;
    private UserJSON user;

    public NewChatJSON(String id, UserJSON user) {
        this.user = user;
        this.id = id;
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

    public void setUser(UserJSON user) {
        this.user = user;
    }
}
