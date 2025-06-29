package com.example.postgres.demo.dto;

import java.util.UUID;

public class ChatsGet {
    private UUID user;

    public UUID getUser() {
        return user;
    }

    public void setUser(UUID user) {
        this.user = user;
    }
}
