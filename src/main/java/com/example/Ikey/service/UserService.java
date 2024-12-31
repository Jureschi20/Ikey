package com.example.Ikey.service;

import com.example.Ikey.exceptie.UserAlreadyExistsException;
import com.example.Ikey.exceptie.UserNotFoundException;
import com.example.Ikey.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.example.Ikey.model.User;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists with username: " + user.getUsername());
        }
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
    }
}

