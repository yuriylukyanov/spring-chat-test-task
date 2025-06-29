package com.example.postgres.demo.dto;

import com.example.postgres.demo.entities.ChatMember;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AddChat {
    private String name;
    private List<UUID> users;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<UUID> getUsers() {
        return users;
    }
    public void setUsers(List<UUID> users) {
        this.users = users;
    }
}

