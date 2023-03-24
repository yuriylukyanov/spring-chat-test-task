package com.pxp.SQLite.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pxp.SQLite.demo.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    public boolean existsByUsername(String username);

    public List<User> findByIdIn(List<String> ids);
}
