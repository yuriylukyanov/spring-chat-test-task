package com.pxp.SQLite.demo.entities;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private String id;

    private String username;

    private String created_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
