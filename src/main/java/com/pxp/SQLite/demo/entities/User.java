package com.pxp.SQLite.demo.entities;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class User {

    @Id
    public String id;

    public String username;

    public String created_at;
}
