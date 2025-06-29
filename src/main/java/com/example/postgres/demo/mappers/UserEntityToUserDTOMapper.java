package com.example.postgres.demo.mappers;

import com.example.postgres.demo.dto.MessageDTO;
import com.example.postgres.demo.dto.UserDTO;
import com.example.postgres.demo.entities.Message;
import com.example.postgres.demo.entities.User;

public class UserEntityToUserDTOMapper {
    public static void map(User user, UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setCreatedAt(user.getCreatedAt());
    }
}
