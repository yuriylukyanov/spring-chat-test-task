package com.example.postgres.demo.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.postgres.demo.entities.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, UUID>  {
    @Query(value = "select c.chat from ChatMember c where c.user.id = :userId order by c.chat.createdAt desc")
    List<Chat> getChatsByUserId(UUID userId);
}
