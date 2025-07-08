package com.example.postgres.demo.services.kafka.addMessage;

import com.example.postgres.demo.dto.AddMessage;
import com.example.postgres.demo.entities.Message;
import com.example.postgres.demo.exceptions.BadRequestException;
import com.example.postgres.demo.repositories.ChatMemberRepository;
import com.example.postgres.demo.repositories.ChatRepository;
import com.example.postgres.demo.repositories.MessageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AddMessageHandler {
    private final ChatRepository chatRepository;
    private final ChatMemberRepository chatMemberRepository;
    private final MessageRepository messageRepository;


    public AddMessageHandler(
            ChatRepository chatRepository,
            ChatMemberRepository chatMemberRepository,
            MessageRepository messageRepository) {
        this.chatRepository = chatRepository;
        this.chatMemberRepository = chatMemberRepository;
        this.messageRepository = messageRepository;
    }

    @Transactional
    public UUID createMessage(AddMessage dto) {
        
        var chat = chatRepository.findAllById(List.of(dto.getChat()));

        var member = chatMemberRepository.findChatMember(dto.getAuthor(), dto.getChat());

        var message = new Message();
        message.setId(UUID.randomUUID());
        message.setAuthor(member.getFirst());
        message.setChat(chat.getFirst());
        message.setText(dto.getText());
        message.setCreatedAt(OffsetDateTime.now());

        messageRepository.save(message);

        return message.getId();
    }
}
