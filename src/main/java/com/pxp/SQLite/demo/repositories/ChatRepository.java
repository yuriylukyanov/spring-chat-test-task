package com.pxp.SQLite.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pxp.SQLite.demo.entities.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, String>  {

    @Query(value = "select * from chat c join chat_member m on c.id = m.chat_id join user u on m.user_id = u.id where m.user_id = ?1 order by created_at desc", nativeQuery = true)
    List<Chat> getChatsByUserId(String userId);
    
}
