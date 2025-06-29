package com.example.postgres.demo.mappers;

import com.example.postgres.demo.dto.MessageDTO;
import com.example.postgres.demo.dto.UserDTO;
import com.example.postgres.demo.entities.Message;

public class MessageEntityToMessageDTOMapper {
    public static void map(Message message, MessageDTO messageDTO) {
        messageDTO.setId(message.getId());
        messageDTO.setText(message.getText());
        messageDTO.setCreatedAt(message.getCreatedAt());
        
        var userDTO = new UserDTO();
        UserEntityToUserDTOMapper.map(message.getAuthor().getUser(), userDTO);
        messageDTO.setAuthor(userDTO);
    }
}
