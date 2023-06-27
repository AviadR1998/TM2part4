package com.example.tm2ex3.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MessageDB {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String chatId;
    private String message;

    private String sender;

    public MessageDB(String chatId, String message, String sender) {
        this.chatId = chatId;
        this.message = message;
        this.sender = sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChatId() {
        return chatId;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
