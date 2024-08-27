package com.example.wed_connect.registration.service;

import com.example.wed_connect.registration.model.User;
import com.example.wed_connect.registration.model.UserType;
import com.example.wed_connect.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(User user, UserType userType) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Username is already taken";
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "Passwords do not match";
        }

        user.setType(userType);
        userRepository.save(user);
        return "User registered successfully";
    }

    public String authenticateUser(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            return "Invalid username";
        }

        if (!userRepository.existsByPassword(user.getPassword())) {
            return "Invalid password";
        }

        return "User logged in successfully";
    }
}
