package com.broker.BrokerApp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String userId;
    private String email;
    private String password;
    private String role;

    public User(String userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }
}
