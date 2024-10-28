package com.example.wed_connect.registration.controller;

import com.example.wed_connect.registration.model.Client;
import com.example.wed_connect.registration.model.Wedding;
import com.example.wed_connect.registration.service.ClientService;
import com.example.wed_connect.registration.service.WeddingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;
    private final WeddingService weddingService;

    public ClientController(ClientService clientService, WeddingService weddingService) {
        this.clientService = clientService;
        this.weddingService = weddingService;
    }

    @GetMapping("/profile/{clientId}")
    public String getClientProfile(@PathVariable Long clientId, Model model) {
        Client client = clientService.findById(clientId);
        Wedding wedding = weddingService.findByClientId(clientId);
        model.addAttribute("client", client);
        model.addAttribute("wedding", wedding);
        return "client/profile";
    }

    @PostMapping("/profile/{clientId}")
    public String updateClientProfile(@PathVariable Long clientId,
                                      @ModelAttribute Client client,
                                      @ModelAttribute Wedding wedding) {
        clientService.updateClientProfile(clientId, client.getName(), client.getPhoneNumber());

        Wedding existingWedding = weddingService.findByClientId(clientId);
        long weddingId = existingWedding.getId();

        weddingService.updateWeddingInfo(
                weddingId,
                wedding.getNameBride(),
                wedding.getNameGroom(),
                wedding.getPhoneNumberBride(),
                wedding.getPhoneNumberGroom(),
                wedding.getDateWedding(),
                wedding.getLocation(),
                wedding.getNumberOfGuests()
        );

        return "redirect:/client/profile/" + clientId;
    }

    @GetMapping("/home/{clientId}")
    public String clientHome(@PathVariable Long clientId, Model model) {
        Client client = clientService.findById(clientId);
        Wedding wedding = weddingService.findByClientId(clientId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        String formattedDate = wedding.getDateWedding().format(formatter);

        model.addAttribute("client", client);
        model.addAttribute("wedding", wedding);
        model.addAttribute("formattedDate", formattedDate);
        return "client/home";
    }
}
