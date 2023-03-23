package com.pxp.SQLite.demo.controllers;

import com.pxp.SQLite.demo.dto.AddUser;
import com.pxp.SQLite.demo.entities.User;
import com.pxp.SQLite.demo.exceptions.BadRequestException;
import com.pxp.SQLite.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "add")
    public ResponseEntity createUser(@RequestBody AddUser dto) throws Exception{
        try {
            return ResponseEntity.ok(userService.createUser(dto));
        } catch(BadRequestException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
