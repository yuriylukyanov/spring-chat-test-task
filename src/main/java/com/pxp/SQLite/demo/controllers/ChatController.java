package com.pxp.SQLite.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pxp.SQLite.demo.dto.AddChat;
import com.pxp.SQLite.demo.dto.ChatsGet;
import com.pxp.SQLite.demo.exceptions.BadRequestException;
import com.pxp.SQLite.demo.services.ChatService;

@RestController
@RequestMapping("chats")
public class ChatController {
    private ChatService chatService;
    
    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping(value = "add")
    public ResponseEntity createChat(@RequestBody AddChat dto) throws Exception{
        try {
            return ResponseEntity.ok(chatService.createChat(dto));
        } catch(BadRequestException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "get")
    public ResponseEntity getChats(@RequestBody ChatsGet dto) throws Exception{
        try {
            var chats = chatService.getChats(dto);
            return ResponseEntity.ok(chats);
        } catch(BadRequestException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
