package com.wed_connect.backend.controller;

import com.wed_connect.backend.dto.RestaurantDTO;
import com.wed_connect.backend.model.Wedding;
import com.wed_connect.backend.service.ClientService;
import com.wed_connect.backend.service.RestaurantService;
import com.wed_connect.backend.service.WeddingService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/services")
public class ServiceController {
    private final RestaurantService restaurantService;
    private final WeddingService weddingService;
    private final ClientService clientService;

    public ServiceController(RestaurantService restaurantService,
                             WeddingService weddingService,
                             ClientService clientService) {
        this.restaurantService = restaurantService;
        this.weddingService = weddingService;
        this.clientService = clientService;
    }

    @GetMapping("/find-services")
    public String findAllRestaurants(Model model,
                                     @AuthenticationPrincipal UserDetails userDetails) {
        Long clientId = clientService.getClientIdByUsername(userDetails.getUsername());
        Wedding wedding = weddingService.findByClientId(clientId);
        LocalDate weddingDate = wedding.getDateWedding();

        List<RestaurantDTO> restaurants = restaurantService.getAllRestaurants().stream()
                .map(r -> {
                    boolean isAvailable = !r.getBookedDates().contains(weddingDate) ||
                            (wedding.getRestaurant() != null &&
                                    wedding.getRestaurant().getId().equals(r.getId()));
                    r.setAvailable(isAvailable);
                    return r;
                })
                .collect(Collectors.toList());

        model.addAttribute("restaurants", restaurants);
        model.addAttribute("selectedRestaurantId", wedding.getRestaurant() != null ?
                wedding.getRestaurant().getId() : null);
        model.addAttribute("weddingDate", weddingDate);

        return "services/find-services";
    }

    @PostMapping("/select-restaurant")
    public String selectRestaurant(@RequestParam("restaurantId") Long restaurantId,
                                   @AuthenticationPrincipal UserDetails userDetails,
                                   RedirectAttributes redirectAttributes) {
        try {
            Long clientId = clientService.getClientIdByUsername(userDetails.getUsername());
            weddingService.setRestaurantForWedding(clientId, restaurantId);
            redirectAttributes.addFlashAttribute("success", "Restaurant selected successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to select restaurant: " + e.getMessage());
        }
        return "redirect:/services/find-services";
    }

    @PostMapping("/remove-restaurant")
    public String removeRestaurant(@AuthenticationPrincipal UserDetails userDetails,
                                   RedirectAttributes redirectAttributes) {
        try {
            Long clientId = clientService.getClientIdByUsername(userDetails.getUsername());
            Wedding wedding = weddingService.findByClientId(clientId);
            weddingService.removeRestaurantFromWedding(wedding.getId());
            redirectAttributes.addFlashAttribute("success", "Restaurant removed successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to remove restaurant: " + e.getMessage());
        }
        return "redirect:/services/find-services";
    }

}