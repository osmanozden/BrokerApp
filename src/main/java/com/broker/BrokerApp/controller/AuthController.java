package com.broker.BrokerApp.controller;

import com.broker.BrokerApp.exeption.BusinessException;
import com.broker.BrokerApp.model.User;
import com.broker.BrokerApp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User request) throws BusinessException {
        userService.registerUser(request.getEmail(), request.getPassword());
        return ResponseEntity.ok("User registered successfully.");
    }

}
