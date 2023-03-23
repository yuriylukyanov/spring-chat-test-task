package com.pxp.SQLite.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pxp.SQLite.demo.dto.AddMessage;
import com.pxp.SQLite.demo.dto.MessagesEntryCount;
import com.pxp.SQLite.demo.dto.MessagesGet;
import com.pxp.SQLite.demo.exceptions.BadRequestException;
import com.pxp.SQLite.demo.services.MessageService;

@RestController
@RequestMapping("messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping(value = "add")
    public ResponseEntity createChat(@RequestBody AddMessage dto) throws Exception{
        try {
            return ResponseEntity.ok(messageService.createMessage(dto));
        } catch(BadRequestException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "get")
    public ResponseEntity getChat(@RequestBody MessagesGet dto) throws Exception{
        try {
            return ResponseEntity.ok(messageService.getMessages(dto));
        } catch(BadRequestException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "entry/count")
    public ResponseEntity entryCount(@RequestBody MessagesEntryCount dto) throws Exception{
        try {
            return ResponseEntity.ok(messageService.getEntryCount(dto));
        } catch(BadRequestException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
