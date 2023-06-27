package com.example.tm2ex3;

public class Messages {

    private String message;

    private boolean myMessage;

    public Messages(/*Bitmap img, */String text, boolean myMessage) {
        this.message = text;
        this.myMessage = myMessage;
    }

    public boolean isMyMessage() {
        return myMessage;
    }


    public String getMessage() {
        return message;
    }
}
