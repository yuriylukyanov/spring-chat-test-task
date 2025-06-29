package com.example.postgres.demo.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import com.example.postgres.demo.dto.ChatDTO;
import com.example.postgres.demo.mappers.ChatEntityToChatDTOMapper;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.postgres.demo.dto.AddChat;
import com.example.postgres.demo.dto.ChatsGet;
import com.example.postgres.demo.entities.Chat;
import com.example.postgres.demo.entities.ChatMember;
import com.example.postgres.demo.exceptions.BadRequestException;
import com.example.postgres.demo.repositories.ChatRepository;
import com.example.postgres.demo.repositories.UserRepository;

@Service
public class ChatService {
    private UserRepository userRepository;
    private ChatRepository chatRepository;

    public ChatService(UserRepository userRepository, ChatRepository chatRepository) {
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
    }

    @Transactional
    public UUID createChat(AddChat dto) throws Exception {
        if (dto.getName() == null || dto.getName().isEmpty())
            throw new BadRequestException("empty name");

        if (dto.getUsers() == null || dto.getUsers().isEmpty())
            throw new BadRequestException("empty users");

        var users = userRepository.findByIdIn(dto.getUsers());

        if (users.size() != dto.getUsers().size())
            throw new BadRequestException("users not found");

        var chat = new Chat();
        chat.setId(UUID.randomUUID());
        chat.setName(dto.getName());
        chat.setCreatedAt(OffsetDateTime.now());

        chat = chatRepository.save(chat);
        for (var user : users) {
            var chatMember = new ChatMember();
            chatMember.setId(UUID.randomUUID());
            chatMember.setUser(user);
            chatMember.setChat(chat);
            chat.getUsers().add(chatMember);
        }

        return chat.getId();
    }

    public List<ChatDTO> getChats(ChatsGet dto) throws BadRequestException {
        if (dto.getUser() == null)
            throw new BadRequestException("empty user");
        
        var chats = chatRepository.getChatsByUserId(dto.getUser());

        var chatDtos = chats.stream()
                .map(chat -> {
                    var chatDto = new ChatDTO();
                    ChatEntityToChatDTOMapper.map(chat, chatDto);
                    return chatDto;
                }).toList();

        return chatDtos;
    }
}
