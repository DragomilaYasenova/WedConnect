package com.wed_connect.backend.controller;

import com.wed_connect.backend.dto.RestaurantDTO;
import com.wed_connect.backend.model.Restaurant;
import com.wed_connect.backend.service.RestaurantService;
import com.wed_connect.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final UserService userService;

    public RestaurantController(RestaurantService restaurantService, UserService userService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getRestaurantProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Long restaurantId = getRestaurantIdFromUser(userDetails);
        RestaurantDTO restaurantDTO = restaurantService.getRestaurantDTOById(restaurantId);
        model.addAttribute("restaurantDTO", restaurantDTO);
        return "restaurant/profile";
    }

    @PostMapping("/profile")
    public String updateRestaurantProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @ModelAttribute RestaurantDTO restaurantDTO,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "restaurant/profile";
        }

        Long restaurantId = getRestaurantIdFromUser(userDetails);
        restaurantService.updateRestaurantProfile(restaurantId, restaurantDTO);
        return "redirect:/restaurant/profile";
    }

    @GetMapping("/api/profile")
    @ResponseBody
    public ResponseEntity<RestaurantDTO> getRestaurantProfileApi(@AuthenticationPrincipal UserDetails userDetails) {
        Long restaurantId = getRestaurantIdFromUser(userDetails);
        return ResponseEntity.ok(restaurantService.getRestaurantDTOById(restaurantId));
    }

    @GetMapping("/home")
    public String restaurantHome(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Long restaurantId = getRestaurantIdFromUser(userDetails);
        Restaurant restaurant = restaurantService.findById(restaurantId);
        model.addAttribute("restaurant", restaurant);
        return "restaurant/home";
    }

    private Long getRestaurantIdFromUser(UserDetails userDetails) {
        return restaurantService.getRestaurantIdByUsername(userDetails.getUsername());
    }
}