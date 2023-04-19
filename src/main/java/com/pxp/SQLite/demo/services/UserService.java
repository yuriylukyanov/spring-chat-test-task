package com.pxp.SQLite.demo.services;

import com.pxp.SQLite.demo.dto.AddUser;
import com.pxp.SQLite.demo.entities.User;
import com.pxp.SQLite.demo.exceptions.BadRequestException;
import com.pxp.SQLite.demo.repositories.UserRepository;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public String createUser(AddUser dto) throws Exception{
        try {
            if (dto.getUsername() == null || dto.getUsername().isEmpty())
                throw new BadRequestException("empty username");
            if (!userRepository.existsByUsername(dto.getUsername())){
                var user = new User();
                user.setId(DigestUtils.sha256Hex(dto.getUsername()));
                user.setUsername(dto.getUsername());
                user.setCreated_at(OffsetDateTime.now().toString());
                userRepository.save(user);
                return user.getId();
            } else {
                throw new BadRequestException("username " + dto.getUsername() + " already exists");
            }
        } catch (Exception e){
            throw e;
        }
    }
}
