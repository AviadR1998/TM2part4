package com.example.tm2ex3;

import android.graphics.Bitmap;

public class Chats {
    private String id;
    private String username;
    private String name;
    private String lastMessage;
    private String date;
    private Bitmap profileImage;

    public Chats(String newName, String newLastMessage, String newDate, Bitmap newPic, String id, String username) {
        this.name = newName;
        this.lastMessage = newLastMessage;
        this.date = newDate;
        this.profileImage = newPic;
        this.username = username;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public Bitmap getProfileImage() {
        return profileImage;
    }

    public String getName() {
        return name;
    }


    public void setNewMessage(String newLastMessage, String newDate) {
        this.lastMessage = newLastMessage;
        this.date = newDate;
    }
}
