package com.example.postgres.demo.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.postgres.demo.entities.ChatMember;

@Repository
public interface ChatMemberRepository extends JpaRepository<ChatMember, UUID> {

    @Query(value = "select c from ChatMember c where c.user.id = :authorId and c.chat.id = :chatId")
    List<ChatMember> findChatMember(UUID authorId, UUID chatId);
}
