package com.wed_connect.backend.controller;

import com.wed_connect.backend.model.Client;
import com.wed_connect.backend.model.WeddingAgency;
import com.wed_connect.backend.service.ClientService;
import com.wed_connect.backend.service.UserService;
import com.wed_connect.backend.model.User;
import com.wed_connect.backend.service.WeddingAgencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UserService userService;
    private final ClientService clientService;
    private final WeddingAgencyService weddingAgencyService;

    public LoginController(UserService userService, ClientService clientService, WeddingAgencyService weddingAgencyService) {
        this.userService = userService;
        this.clientService = clientService;
        this.weddingAgencyService = weddingAgencyService;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model) {
        String result = userService.authenticateUser(user);
        String userType = userService.getUserType(user);
        Long userId = userService.returnUserId(user);

        if ("User logged in successfully".equals(result)) {
            String redirectUrl;
            if ("Client".equals(userType)) {
                Client client = clientService.findByUserId(userId);

                if (client.getName() == null || client.getPhoneNumber() == null) {
                    redirectUrl = "/client/profile";
                } else {
                    redirectUrl = "/client/home";
                }

            } else if ("Wedding Agency".equals(userType)) {
                WeddingAgency weddingAgency = weddingAgencyService.findByUserId(userId);

                if (weddingAgency.getName() == null || weddingAgency.getPhoneNumber() == null ||
                        weddingAgency.getAddress() == null || weddingAgency.getMaxDistanceKm() == null) {
                    redirectUrl = "/wedding_agency/profile";
                } else {
                    redirectUrl = "/wedding_agency/home";
                }
            } else {
                redirectUrl = "/";
            }

            return "redirect:" + redirectUrl;
        } else {
            model.addAttribute("errorMessage", result);
            return "login";
        }
    }
}