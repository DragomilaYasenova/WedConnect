package com.example.wed_connect.registration.controller;

import com.example.wed_connect.registration.model.Client;
import com.example.wed_connect.registration.model.WeddingAgency;
import com.example.wed_connect.registration.service.ClientService;
import com.example.wed_connect.registration.service.UserService;
import com.example.wed_connect.registration.model.User;
import com.example.wed_connect.registration.service.WeddingAgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private WeddingAgencyService weddingAgencyService;

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
                System.out.println("Client");
                Client client = clientService.findByUserId(userId);

                if (client.getName() == null || client.getPhoneNumber() == null) {
                    redirectUrl = "/client/info/" + client.getId();
                } else {
                    redirectUrl = "/client/home/" + client.getId();
                }
            } else if ("Wedding Agency".equals(userType)) {
                System.out.println("Wedding Agency");
                WeddingAgency weddingAgency = weddingAgencyService.findByUserId(userId);

                if (weddingAgency.getName() == null || weddingAgency.getPhoneNumber() == null || weddingAgency.getAddress() == null || weddingAgency.getMaxDistanceKm() == null) {
                    redirectUrl = "/wedding_agency/info/" + weddingAgency.getId();
                } else {
                    redirectUrl = "/wedding_agency/home/" + weddingAgency.getId();
                }
            }

            else {
                redirectUrl = "";
            }
            return "redirect:" + redirectUrl;
        } else {
            model.addAttribute("errorMessage", result);
            return "login";
        }
    }
}
