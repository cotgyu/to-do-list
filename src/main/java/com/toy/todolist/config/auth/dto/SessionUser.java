package com.toy.todolist.config.auth.dto;

import com.toy.todolist.user.domain.Role;
import com.toy.todolist.user.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private long id;
    private String name;
    private String email;
    private String picture;
    private Role role;

    public SessionUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.role = user.getRole();
    }
}
