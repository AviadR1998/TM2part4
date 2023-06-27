package com.example.tm2ex3;

public class MessagesJSON {
    private int id;
    private String created;
    private String content;

    public MessagesJSON (int id, String created, String content) {
        this.id = id;
        this.created = created;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getCreated() {
        return created;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setId(int id) {
        this.id = id;
    }
}
