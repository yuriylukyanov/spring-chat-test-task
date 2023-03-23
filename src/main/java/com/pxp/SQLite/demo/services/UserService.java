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

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public String createUser(AddUser dto) throws Exception{
        try {
            if (dto.username == null || dto.username == "")
                throw new BadRequestException("empty username");
            if (!userRepository.existsByUsername(dto.username)){
                var user = new User();
                user.id = DigestUtils.sha256Hex(dto.username);
                user.username = dto.username;
                user.created_at = OffsetDateTime.now().toString();
                userRepository.save(user);
                return user.id;
            } else {
                throw new BadRequestException("username " + dto.username + " already exists");
            }
        } catch (Exception e){
            throw e;
        }
    }

    // public List<User> readStudents(){
    //     return userRepository.findAll();
    // }

    // @Transactional
    // public String updateStudent(User student){
    //     if (userRepository.existsByUsername(student.getEmail())){
    //         try {
    //             List<User> students = userRepository.findByEmail(student.getEmail());
    //             students.stream().forEach(s -> {
    //                 User studentToBeUpdate = userRepository.findById(s.getId()).get();
    //                 studentToBeUpdate.setName(student.getName());
    //                 studentToBeUpdate.setEmail(student.getEmail());
    //                 userRepository.save(studentToBeUpdate);
    //             });
    //             return "Student record updated.";
    //         }catch (Exception e){
    //             throw e;
    //         }
    //     }else {
    //         return "Student does not exists in the database.";
    //     }
    // }

    // @Transactional
    // public String deleteStudent(User student){
    //     if (userRepository.existsByUsername(student.getEmail())){
    //         try {
    //             List<User> students = userRepository.findByEmail(student.getEmail());
    //             students.stream().forEach(s -> {
    //                 userRepository.delete(s);
    //             });
    //             return "Student record deleted successfully.";
    //         }catch (Exception e){
    //             throw e;
    //         }

    //     }else {
    //         return "Student does not exist";
    //     }
    // }
}
