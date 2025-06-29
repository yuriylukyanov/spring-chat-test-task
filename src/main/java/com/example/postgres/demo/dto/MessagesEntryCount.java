package com.example.postgres.demo.dto;

import java.util.UUID;

public class MessagesEntryCount {
    private UUID chat;
    private String text;
    public UUID getChat() {
        return chat;
    }
    public void setChat(UUID chat) {
        this.chat = chat;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
