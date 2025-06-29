package com.example.postgres.demo.services;

import com.example.postgres.demo.dto.AddUser;
import com.example.postgres.demo.entities.User;
import com.example.postgres.demo.exceptions.BadRequestException;
import com.example.postgres.demo.repositories.UserRepository;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UUID createUser(AddUser dto) throws Exception {
        if (dto.getUsername() == null || dto.getUsername().isEmpty())
            throw new BadRequestException("empty username");
        if (!userRepository.existsByUsername(dto.getUsername())) {
            var user = new User();
            user.setId(UUID.randomUUID());
            user.setUsername(dto.getUsername());
            user.setCreatedAt(OffsetDateTime.now());
            userRepository.save(user);
            return user.getId();
        } else {
            throw new BadRequestException("username " + dto.getUsername() + " already exists");
        }
    }
}
