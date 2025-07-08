package com.example.postgres.demo.controllers;

import com.example.postgres.demo.dto.AddUser;
import com.example.postgres.demo.dto.SetLocationDTO;
import com.example.postgres.demo.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "add")
    public ResponseEntity createUser(@RequestBody AddUser dto) throws Exception {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @PostMapping(value = "setLocation")
    public ResponseEntity setLocation(@RequestBody SetLocationDTO dto) throws Exception {
        userService.setLocation(dto);
        return ResponseEntity.ok().build();
    }
}
