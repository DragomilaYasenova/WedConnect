package com.wed_connect.backend.controller;

import com.wed_connect.backend.dto.UserDTO;
import com.wed_connect.backend.model.UserType;
import com.wed_connect.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("userTypes", UserType.values());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                               BindingResult bindingResult,
                               Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("userTypes", UserType.values());
            return "register";
        }

        String result = userService.registerUser(userDTO);
        if ("User registered successfully".equals(result)) {
            model.addAttribute("successMessage", result);
            return "redirect:/login";
        } else {
            model.addAttribute("errorMessage", result);
            model.addAttribute("userTypes", UserType.values());
            return "register";
        }
    }
}