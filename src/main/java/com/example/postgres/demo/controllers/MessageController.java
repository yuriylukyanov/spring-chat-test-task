package com.example.postgres.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgres.demo.dto.AddMessage;
import com.example.postgres.demo.dto.MessagesEntryCount;
import com.example.postgres.demo.dto.MessagesGet;
import com.example.postgres.demo.services.MessageService;

@RestController
@RequestMapping("messages")
public class MessageController {
    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping(value = "add")
    public ResponseEntity createChat(@RequestBody AddMessage dto) throws Exception {
        messageService.createMessage(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "get")
    public ResponseEntity getChat(@RequestBody MessagesGet dto) throws Exception {
        return ResponseEntity.ok(messageService.getMessages(dto));
    }

    @PostMapping(value = "entry/count")
    public ResponseEntity entryCount(@RequestBody MessagesEntryCount dto) throws Exception {
        return ResponseEntity.ok(messageService.getEntryCount(dto));
    }
}
