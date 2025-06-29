package com.example.postgres.demo.mappers;

import com.example.postgres.demo.dto.ChatDTO;
import com.example.postgres.demo.dto.UserDTO;
import com.example.postgres.demo.entities.Chat;
import com.example.postgres.demo.entities.User;

public class ChatEntityToChatDTOMapper {
    public static void map(Chat chat, ChatDTO chatDTO) {
        chatDTO.setId(chat.getId());
        chatDTO.setName(chat.getName());
        chatDTO.setCreatedAt(chat.getCreatedAt());
    }
}

