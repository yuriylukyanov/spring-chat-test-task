package com.pxp.SQLite.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pxp.SQLite.demo.entities.Chat;
import com.pxp.SQLite.demo.entities.ChatMember;
import com.pxp.SQLite.demo.entities.User;

@Repository
public interface ChatMemberRepository extends JpaRepository<ChatMember, String> {

    @Query(value = "select * from chat_member c where c.user_id = ?1 and c.chat_id = ?2", nativeQuery = true)
    List<ChatMember> findChatMember(String author, String chat);
    
}
