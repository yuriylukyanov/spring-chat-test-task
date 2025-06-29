package com.example.postgres.demo.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public class MessageDTO {
    private UUID id;
    private String text;
    private OffsetDateTime createdAt;

    private UserDTO author;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

}
