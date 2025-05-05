package com.wed_connect.backend.controller;

import com.wed_connect.backend.dto.UserDTO;
import com.wed_connect.backend.model.Client;
import com.wed_connect.backend.model.Restaurant;
import com.wed_connect.backend.model.WeddingAgency;
import com.wed_connect.backend.service.ClientService;
import com.wed_connect.backend.service.RestaurantService;
import com.wed_connect.backend.service.UserService;
import com.wed_connect.backend.model.User;
import com.wed_connect.backend.service.WeddingAgencyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final UserService userService;
    private final ClientService clientService;
    private final WeddingAgencyService weddingAgencyService;
    private final RestaurantService restaurantService;

    public LoginController(UserService userService, ClientService clientService,
                           WeddingAgencyService weddingAgencyService, RestaurantService restaurantService) {
        this.userService = userService;
        this.clientService = clientService;
        this.weddingAgencyService = weddingAgencyService;
        this.restaurantService = restaurantService;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                            BindingResult bindingResult,
                            Model model) {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        String result = userService.authenticateUser(userDTO);
        String userType = userService.getUserType(userDTO.getUsername());
        Long userId = userService.returnUserIdByUsername(userDTO.getUsername());

        if ("User logged in successfully".equals(result)) {
            String redirectUrl = determineRedirectUrl(userType, userId);
            return "redirect:" + redirectUrl;
        } else {
            model.addAttribute("errorMessage", result);
            return "login";
        }
    }

    private String determineRedirectUrl(String userType, Long userId) {
        return switch (userType) {
            case "Client" -> {
                Client client = clientService.findByUserId(userId);
                yield (client.getName() == null || client.getPhoneNumber() == null)
                        ? "/client/profile" : "/client/home";
            }
            case "Wedding Agency" -> {
                WeddingAgency agency = weddingAgencyService.findByUserId(userId);
                yield (agency.getName() == null || agency.getPhoneNumber() == null ||
                        agency.getAddress() == null || agency.getMaxDistanceKm() == null)
                        ? "/wedding_agency/profile" : "/wedding_agency/home";
            }
            case "Restaurant" -> {
                Restaurant restaurant = restaurantService.findByUserId(userId);
                yield (restaurant.getName() == null || restaurant.getPhoneNumber() == null ||
                        restaurant.getAddress() == null || restaurant.getCapacity() == null)
                        ? "/restaurant/profile" : "/restaurant/home";
            }
            default -> "/";
        };
    }
}