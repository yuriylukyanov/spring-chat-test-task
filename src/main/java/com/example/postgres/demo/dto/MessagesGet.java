package com.example.postgres.demo.dto;

import java.util.UUID;

public class MessagesGet {
    private UUID chat;

    public UUID getChat() {
        return chat;
    }

    public void setChat(UUID chat) {
        this.chat = chat;
    } 
}
