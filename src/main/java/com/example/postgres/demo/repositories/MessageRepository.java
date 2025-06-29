package com.example.postgres.demo.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.postgres.demo.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

    @Query(value = "select m from Message m where m.chat.id = :chat order by createdAt")
    List<Message> getAllByChatId(UUID chat);

    @Query(value = "select count(m) from Message m where m.chat.id = :chat and m.text like %:text%")
    int entryCount(UUID chat, String text);
    
}
