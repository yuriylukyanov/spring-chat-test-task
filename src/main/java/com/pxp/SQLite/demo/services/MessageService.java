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
    private ChatRepository chatRepository;
    private ChatMemberRepository chatMemberRepository;
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(ChatRepository chatRepository, ChatMemberRepository chatMemberRepository,
            MessageRepository messageRepository) {
        this.chatRepository = chatRepository;
        this.chatMemberRepository = chatMemberRepository;
        this.messageRepository = messageRepository;
    }

    @Transactional
    public String createMessage(AddMessage dto) throws BadRequestException {
        if (dto.getAuthor() == null || dto.getAuthor().isEmpty())
            throw new BadRequestException("empty author");
        
        if (dto.getChat() == null || dto.getChat().isEmpty())
            throw new BadRequestException("empty chat");

        if (dto.getText() == null || dto.getText().isEmpty())
            throw new BadRequestException("empty text");

        var chat = chatRepository.findAllById(List.of(dto.getChat()));
        if (chat.isEmpty())
            throw new BadRequestException("chat not found");

        var member = chatMemberRepository.findChatMember(dto.getAuthor(), dto.getChat());
        if (member.isEmpty())
            throw new BadRequestException("author not found");
        
        var message = new Message();
        message.setId(UUID.randomUUID().toString());
        message.setAuthor(member.get(0));
        message.setChat(chat.get(0));
        message.setText(dto.getText());
        message.setCreated_at(OffsetDateTime.now().toInstant().toEpochMilli());

        messageRepository.save(message);

        return message.getId();
    }

    public List<Message> getMessages(MessagesGet dto) throws BadRequestException {
        if (dto.getChat() == null || dto.getChat().isEmpty())
            throw new BadRequestException("empty chat");
        
        var messages = messageRepository.getAllByChatId(dto.getChat());
        return messages;
    }

    public int getEntryCount(MessagesEntryCount dto) throws BadRequestException {
        if (dto.getChat() == null || dto.getChat().isEmpty())
            throw new BadRequestException("empty chat");
        if (dto.getText() == null)
            dto.setText("");

        var count = messageRepository.entryCount(dto.getChat(), dto.getText());
        return count;
    }
    
}
