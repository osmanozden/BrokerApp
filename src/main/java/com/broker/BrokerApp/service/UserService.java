package com.broker.BrokerApp.service;

import com.broker.BrokerApp.exeption.BusinessException;
import com.broker.BrokerApp.repository.UserRepository;
import com.broker.BrokerApp.util.PasswordUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(String username, String password) throws BusinessException {
        if (userRepository.existsByUsername(username)) {
            throw new BusinessException("Username already exists: " + username, HttpStatus.BAD_REQUEST.value());
        }
        userRepository.save(username, PasswordUtil.hashSHA256(password));
    }

    public void loginUser(String username, String password) throws BusinessException {
        if (!userRepository.existsByUsername(username)) {
            throw new BusinessException("Invalid username or password", HttpStatus.UNAUTHORIZED.value());
        }

        if (!userRepository.checkPassword(username, PasswordUtil.hashSHA256(password))) {
            throw new BusinessException("Invalid username or password", HttpStatus.UNAUTHORIZED.value());
        }
    }
}
