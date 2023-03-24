package com.pxp.SQLite.demo.entities;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    public String id;

    public String username;

    public String created_at;
}
