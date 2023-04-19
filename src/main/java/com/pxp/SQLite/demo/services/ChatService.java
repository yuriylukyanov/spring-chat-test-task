package com.pxp.SQLite.demo.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pxp.SQLite.demo.dto.AddChat;
import com.pxp.SQLite.demo.dto.ChatsGet;
import com.pxp.SQLite.demo.entities.Chat;
import com.pxp.SQLite.demo.entities.ChatMember;
import com.pxp.SQLite.demo.exceptions.BadRequestException;
import com.pxp.SQLite.demo.repositories.ChatRepository;
import com.pxp.SQLite.demo.repositories.UserRepository;

@Service
public class ChatService {
    private UserRepository userRepository;
    private ChatRepository chatRepository;
    
    @Autowired
    public ChatService(UserRepository userRepository, ChatRepository chatRepository) {
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
    }

    @Transactional
    public String createChat(AddChat dto) throws Exception{
        try {
            if (dto.getName() == null || dto.getName().isEmpty())
                throw new BadRequestException("empty name");

            if (dto.getUsers() == null || dto.getUsers().isEmpty())
                throw new BadRequestException("empty users");
            
            var users = userRepository.findByIdIn(dto.getUsers());

            if (users.size() != dto.getUsers().size()) 
                throw new BadRequestException("users not found");

            var chat = new Chat();
            chat.setId(UUID.randomUUID().toString());
            chat.setName(dto.getName());
            chat.setCreated_at(OffsetDateTime.now().toInstant().toEpochMilli());
            
            chat = chatRepository.save(chat);
            for (var user : users) {
                var chatMember = new ChatMember();
                chatMember.setId(DigestUtils.sha256Hex(user.getId() + chat.getId()));
                chatMember.setUser(user);
                chat.getUsers().add(chatMember);
            }

            return chat.getId();
        } catch (Exception e){
            throw e;
        }
    }

    public List<Chat> getChats(ChatsGet dto) throws BadRequestException {
        if (dto.getUser() == null || dto.getUser().isEmpty())
            throw new BadRequestException("empty user");
        
        var chats = chatRepository.getChatsByUserId(dto.getUser());

        return chats;
    }
}
