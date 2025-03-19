package com.wed_connect.backend.controller;

import com.wed_connect.backend.model.UserType;
import com.wed_connect.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.wed_connect.backend.model.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;


@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("userTypes", UserType.values());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, @ModelAttribute("userType") UserType userType, Model model) {
        String result = userService.registerUser(user, userType);
        if (result.equals("User registered successfully")) {
            model.addAttribute("successMessage", result);
            return "redirect:/login";
        } else {
            model.addAttribute("errorMessage", result);
        }
        model.addAttribute("userTypes", UserType.values());
        return "register";
    }
}