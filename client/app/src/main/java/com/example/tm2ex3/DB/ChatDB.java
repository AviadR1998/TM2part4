package com.example.tm2ex3.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatDB {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String chatId;
    private String username;
    private String name;
    private String lastMessage;
    private String date;
    private String profilePic;

    public ChatDB(String name, String lastMessage, String date, String profilePic, String chatId, String username) {
        this.name = name;
        this.lastMessage = lastMessage;
        this.date = date;
        this.profilePic = profilePic;
        this.username = username;
        this.chatId = chatId;
    }

    public String getUsername() {
        return username;
    }

    public String getChatId() {
        return chatId;
    }

    public int getId() {
        return id;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public String getName() {
        return name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getDate() {
        return date;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setId(String chatId) {
        this.chatId = chatId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }
}
