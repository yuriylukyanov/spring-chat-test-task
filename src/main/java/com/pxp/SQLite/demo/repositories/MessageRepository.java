package com.pxp.SQLite.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pxp.SQLite.demo.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {

    @Query(value = "select * from message m join chat c on c.id = m.chat_id join chat_member cm on m.author_id = cm.id join user u on cm.user_id = u.id where m.chat_id = ?1 order by created_at", nativeQuery = true)
    List<Message> getAllByChatId(String chat);

    @Query(value = "select count(*) from message m where m.chat_id = ?1 and m.text like ?2", nativeQuery = true)
    int entryCount(String chat, String text);
    
}
