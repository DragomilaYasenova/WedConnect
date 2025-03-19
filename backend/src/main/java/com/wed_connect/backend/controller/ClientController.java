package com.wed_connect.backend.controller;

import com.wed_connect.backend.model.Client;
import com.wed_connect.backend.model.ClientWeddingForm;
import com.wed_connect.backend.model.Wedding;
import com.wed_connect.backend.service.ClientService;
import com.wed_connect.backend.service.UserService;
import com.wed_connect.backend.service.WeddingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final WeddingService weddingService;
    private final UserService userService;

    public ClientController(ClientService clientService, WeddingService weddingService, UserService userService) {
        this.clientService = clientService;
        this.weddingService = weddingService;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getClientProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Long clientId = getClientIdFromUser(userDetails);
        Client client = clientService.findById(clientId);
        Wedding wedding = weddingService.findByClientId(clientId);

        ClientWeddingForm clientWeddingForm = new ClientWeddingForm();
        clientWeddingForm.setClient(client);
        clientWeddingForm.setWedding(wedding);

        model.addAttribute("clientWeddingForm", clientWeddingForm);
        return "client/profile";
    }

    @PostMapping("/profile")
    public String updateClientProfile(@AuthenticationPrincipal UserDetails userDetails,
                                      @Valid @ModelAttribute ClientWeddingForm clientWeddingForm,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "client/profile";
        }

        Client client = clientWeddingForm.getClient();
        Wedding wedding = clientWeddingForm.getWedding();
        Long clientId = getClientIdFromUser(userDetails);

        clientService.updateClientProfile(clientId, client.getName(), client.getPhoneNumber());
        Wedding existingWedding = weddingService.findByClientId(clientId);

        weddingService.updateWeddingInfo(
                existingWedding.getId(),
                wedding.getNameBride(),
                wedding.getNameGroom(),
                wedding.getPhoneNumberBride(),
                wedding.getPhoneNumberGroom(),
                wedding.getDateWedding(),
                wedding.getLocation(),
                wedding.getNumberOfGuests()
        );

        return "redirect:/client/profile";
    }

    @GetMapping("/home")
    public String clientHome(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Long clientId = getClientIdFromUser(userDetails);
        Client client = clientService.findById(clientId);
        Wedding wedding = weddingService.findByClientId(clientId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd / MM / yyyy");
        String formattedDate = wedding.getDateWedding().format(formatter);

        model.addAttribute("client", client);
        model.addAttribute("wedding", wedding);
        model.addAttribute("formattedDate", formattedDate);
        return "client/home";
    }

    private Long getClientIdFromUser(UserDetails userDetails) {
        Long userId = userService.returnUserIdByUsername(userDetails.getUsername());
        Client client = clientService.findByUserId(userId);

        if (client == null) {
            throw new IllegalStateException("No client found for user: " + userDetails.getUsername());
        }
        return client.getId();
    }
}
