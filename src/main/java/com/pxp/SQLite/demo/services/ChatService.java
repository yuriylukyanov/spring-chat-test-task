package com.pxp.SQLite.demo.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.codec.cli.Digest;
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
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Transactional
    public String createChat(AddChat dto) throws Exception{
        try {
            if (dto.name == null || dto.name == "")
                throw new BadRequestException("empty name");

            if (dto.users == null || dto.users.isEmpty())
                throw new BadRequestException("empty users");
            
            var users = userRepository.findByIdIn(dto.users);

            if (users.size() != dto.users.size()) 
                throw new BadRequestException("users not found");

            
            var chat = new Chat();
            chat.id = UUID.randomUUID().toString();
            chat.name = dto.name;
            chat.created_at = OffsetDateTime.now().toInstant().toEpochMilli();
            
            chat = chatRepository.save(chat);
            for (var user : users) {
                var chatMember = new ChatMember();
                chatMember.id = DigestUtils.sha256Hex(user.id + chat.id);
                chatMember.user = user;
                chat.users.add(chatMember);
            }

            return chat.id;
        } catch (Exception e){
            throw e;
        }
    }

    public List<Chat> getChats(ChatsGet dto) throws BadRequestException {
        if (dto.user == null || dto.user == "")
            throw new BadRequestException("empty user");
        
        var chats = chatRepository.getChatsByUserId(dto.user);

        return chats;
    }
}
