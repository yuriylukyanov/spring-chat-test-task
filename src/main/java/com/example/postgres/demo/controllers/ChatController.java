package com.example.postgres.demo.controllers;

import com.example.postgres.demo.services.OpenStreetMapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.postgres.demo.dto.AddChat;
import com.example.postgres.demo.dto.ChatsGet;
import com.example.postgres.demo.services.ChatService;

@RestController
@RequestMapping("chats")
public class ChatController {
    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping(value = "add")
    public ResponseEntity createChat(@RequestBody AddChat dto) throws Exception {
        return ResponseEntity.ok(chatService.createChat(dto));
    }

    @PostMapping(value = "get")
    public ResponseEntity getChats(@RequestBody ChatsGet dto) throws Exception {
        var chats = chatService.getChats(dto);
        return ResponseEntity.ok(chats);
    }
}
