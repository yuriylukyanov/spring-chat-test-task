package com.pxp.SQLite.demo.dto;

import java.util.List;

public class AddChat {
    private String name;
    private List<String> users;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<String> getUsers() {
        return users;
    }
    public void setUsers(List<String> users) {
        this.users = users;
    }
}
