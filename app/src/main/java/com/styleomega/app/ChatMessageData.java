package com.styleomega.app;

public class ChatMessageData {
    public String message;
    public boolean isUser;

    public ChatMessageData(String message, boolean isUser) {
        this.message = message;
        this.isUser = isUser;
    }
}
