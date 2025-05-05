package com.wed_connect.backend.controller;

import com.wed_connect.backend.dto.*;
import com.wed_connect.backend.model.Client;
import com.wed_connect.backend.model.Wedding;
import com.wed_connect.backend.service.ClientService;
import com.wed_connect.backend.service.RestaurantService;
import com.wed_connect.backend.service.UserService;
import com.wed_connect.backend.service.WeddingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final WeddingService weddingService;
    private final RestaurantService restaurantService;
    private final UserService userService;

    public ClientController(ClientService clientService, WeddingService weddingService, RestaurantService restaurantService, UserService userService) {
        this.clientService = clientService;
        this.weddingService = weddingService;
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getClientProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Long clientId = getClientIdFromUser(userDetails);

        ClientDTO clientDTO = clientService.getClientDTOById(clientId);
        WeddingDTO weddingDTO = weddingService.getWeddingDTOByClientId(clientId);

        model.addAttribute("clientWeddingForm", new ClientWeddingForm(clientDTO, weddingDTO));
        return "client/profile";
    }


    @PostMapping("/profile")
    public String updateClientProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @ModelAttribute ClientWeddingForm clientWeddingForm,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "client/profile";
        }

        Long clientId = getClientIdFromUser(userDetails);

        clientService.updateClientProfile(clientId, clientWeddingForm.getClient());
        weddingService.updateWedding(clientId, clientWeddingForm.getWedding());

        return "redirect:/client/profile";
    }


    @GetMapping("/api/profile")
    @ResponseBody
    public ResponseEntity<ClientWeddingForm> getClientProfileApi(@AuthenticationPrincipal UserDetails userDetails) {
        Long clientId = getClientIdFromUser(userDetails);

        ClientDTO clientDTO = clientService.getClientDTOById(clientId);
        WeddingDTO weddingDTO = weddingService.getWeddingDTOByClientId(clientId);

        return ResponseEntity.ok(new ClientWeddingForm(clientDTO, weddingDTO));
    }


    @GetMapping("/home")
    public String clientHome(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Long clientId = getClientIdFromUser(userDetails);

        Client client = clientService.findById(clientId);
        Wedding wedding = weddingService.findByClientId(clientId);

        String formattedDate = wedding.getDateWedding()
                .format(DateTimeFormatter.ofPattern("dd / MM / yyyy"));

        model.addAttribute("client", client);
        model.addAttribute("wedding", wedding);
        model.addAttribute("formattedDate", formattedDate);
        return "client/home";
    }

    private Long getClientIdFromUser(UserDetails userDetails) {
        return clientService.getClientIdByUsername(userDetails.getUsername());
    }
}
