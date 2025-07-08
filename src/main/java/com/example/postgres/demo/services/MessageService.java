package com.example.postgres.demo.services;

import java.util.List;

import com.example.postgres.demo.dto.MessageDTO;
import com.example.postgres.demo.mappers.MessageEntityToMessageDTOMapper;
import com.example.postgres.demo.services.kafka.addMessage.AddMessageKafkaSender;

import org.springframework.stereotype.Service;

import com.example.postgres.demo.dto.AddMessage;
import com.example.postgres.demo.dto.MessagesEntryCount;
import com.example.postgres.demo.dto.MessagesGet;
import com.example.postgres.demo.exceptions.BadRequestException;
import com.example.postgres.demo.repositories.ChatMemberRepository;
import com.example.postgres.demo.repositories.ChatRepository;
import com.example.postgres.demo.repositories.MessageRepository;

@Service
public class MessageService {
    private final ChatRepository chatRepository;
    private final ChatMemberRepository chatMemberRepository;
    private final MessageRepository messageRepository;
    private final AddMessageKafkaSender messageSender;

    public MessageService(
            ChatRepository chatRepository,
            ChatMemberRepository chatMemberRepository,
            MessageRepository messageRepository,
            AddMessageKafkaSender messageSender) {
        this.chatRepository = chatRepository;
        this.chatMemberRepository = chatMemberRepository;
        this.messageRepository = messageRepository;
        this.messageSender = messageSender;
    }

    public void createMessage(AddMessage dto) throws BadRequestException {
        if (dto.getAuthor() == null)
            throw new BadRequestException("empty author");
        
        if (dto.getChat() == null)
            throw new BadRequestException("empty chat");

        if (dto.getText() == null || dto.getText().isEmpty())
            throw new BadRequestException("empty text");

        var chat = chatRepository.findAllById(List.of(dto.getChat()));
        if (chat.isEmpty())
            throw new BadRequestException("chat not found");

        var member = chatMemberRepository.findChatMember(dto.getAuthor(), dto.getChat());
        if (member.isEmpty())
            throw new BadRequestException("author not found");

        messageSender.sendMessage(dto);
    }

    public List<MessageDTO> getMessages(MessagesGet dto) throws BadRequestException {
        if (dto.getChat() == null)
            throw new BadRequestException("empty chat");
        
        var messages = messageRepository.getAllByChatId(dto.getChat());

        var dtos = messages.stream().map(message -> {
            var messageDTO = new MessageDTO();
            MessageEntityToMessageDTOMapper.map(message, messageDTO);
            return messageDTO;
        }).toList();

        return dtos;
    }

    public int getEntryCount(MessagesEntryCount dto) throws BadRequestException {
        if (dto.getChat() == null)
            throw new BadRequestException("empty chat");
        if (dto.getText() == null)
            dto.setText("");

        var count = messageRepository.entryCount(dto.getChat(), dto.getText());
        return count;
    }
    
}
