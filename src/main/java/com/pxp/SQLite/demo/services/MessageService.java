package com.pxp.SQLite.demo.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pxp.SQLite.demo.dto.AddMessage;
import com.pxp.SQLite.demo.dto.MessagesEntryCount;
import com.pxp.SQLite.demo.dto.MessagesGet;
import com.pxp.SQLite.demo.entities.Message;
import com.pxp.SQLite.demo.exceptions.BadRequestException;
import com.pxp.SQLite.demo.repositories.ChatMemberRepository;
import com.pxp.SQLite.demo.repositories.ChatRepository;
import com.pxp.SQLite.demo.repositories.MessageRepository;

@Service
public class MessageService {
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatMemberRepository chatMemberRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Transactional
    public String createMessage(AddMessage dto) throws BadRequestException {
        if (dto.author == null || dto.author == "")
            throw new BadRequestException("empty author");
        
        if (dto.chat == null || dto.chat == "")
            throw new BadRequestException("empty chat");

        if (dto.text == null || dto.text == "")
            throw new BadRequestException("empty text");

        var chat = chatRepository.findAllById(List.of(dto.chat));
        if (chat.isEmpty())
            throw new BadRequestException("chat not found");

        var member = chatMemberRepository.findChatMember(dto.author, dto.chat);
        if (member.isEmpty())
            throw new BadRequestException("author not found");
        
        var message = new Message();
        message.id = UUID.randomUUID().toString();
        message.author = member.get(0);
        message.chat = chat.get(0);
        message.text = dto.text;
        message.created_at = OffsetDateTime.now().toInstant().toEpochMilli();

        messageRepository.save(message);

        return message.id;
    }

    public List<Message> getMessages(MessagesGet dto) throws BadRequestException {
        if (dto.chat == null || dto.chat == "")
            throw new BadRequestException("empty chat");
        
        var messages = messageRepository.getAllByChatId(dto.chat);
        return messages;
    }

    public int getEntryCount(MessagesEntryCount dto) throws BadRequestException {
        if (dto.chat == null || dto.chat == "")
            throw new BadRequestException("empty chat");
        if (dto.text == null)
            dto.text = "";

        var count = messageRepository.entryCount(dto.chat, dto.text);
        return count;
    }
    
}
