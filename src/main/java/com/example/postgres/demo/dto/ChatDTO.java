package com.example.postgres.demo.dto;

import com.example.postgres.demo.entities.Chat;
import com.example.postgres.demo.entities.ChatMember;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ChatDTO {
    private UUID id;
    private String name;
    private OffsetDateTime createdAt;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

