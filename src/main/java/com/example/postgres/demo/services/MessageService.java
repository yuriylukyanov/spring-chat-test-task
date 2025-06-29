package com.example.postgres.demo.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import com.example.postgres.demo.dto.MessageDTO;
import com.example.postgres.demo.mappers.MessageEntityToMessageDTOMapper;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.postgres.demo.dto.AddMessage;
import com.example.postgres.demo.dto.MessagesEntryCount;
import com.example.postgres.demo.dto.MessagesGet;
import com.example.postgres.demo.entities.Message;
import com.example.postgres.demo.exceptions.BadRequestException;
import com.example.postgres.demo.repositories.ChatMemberRepository;
import com.example.postgres.demo.repositories.ChatRepository;
import com.example.postgres.demo.repositories.MessageRepository;

@Service
public class MessageService {
    private ChatRepository chatRepository;
    private ChatMemberRepository chatMemberRepository;
    private MessageRepository messageRepository;

    public MessageService(ChatRepository chatRepository,
          ChatMemberRepository chatMemberRepository,
          MessageRepository messageRepository) {
        this.chatRepository = chatRepository;
        this.chatMemberRepository = chatMemberRepository;
        this.messageRepository = messageRepository;
    }

    @Transactional
    public UUID createMessage(AddMessage dto) throws BadRequestException {
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
        
        var message = new Message();
        message.setId(UUID.randomUUID());
        message.setAuthor(member.getFirst());
        message.setChat(chat.getFirst());
        message.setText(dto.getText());
        message.setCreatedAt(OffsetDateTime.now());

        messageRepository.save(message);

        return message.getId();
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
