package com.example.postgres.demo.dto;

import java.util.UUID;

public class AddMessage {
    private UUID chat;
    private UUID author;
    private String text;
    public UUID getChat() {
        return chat;
    }
    public void setChat(UUID chat) {
        this.chat = chat;
    }
    public UUID getAuthor() {
        return author;
    }
    public void setAuthor(UUID author) {
        this.author = author;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
