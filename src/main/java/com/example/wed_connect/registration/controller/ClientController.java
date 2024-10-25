package com.example.wed_connect.registration.controller;

import com.example.wed_connect.registration.model.Client;
import com.example.wed_connect.registration.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/profile/{clientId}")
    public String showClientProfile(@PathVariable Long clientId, Model model) {
        Client client = clientService.findById(clientId);
        model.addAttribute("client", client);
        return "client/profile";
    }

    @PostMapping("/profile/{clientId}")
    public String updateClientProfile(@PathVariable Long clientId, @ModelAttribute Client client) {
        clientService.updateClientProfile(clientId, client.getName(), client.getPhoneNumber());
        return "client/profile";
    }

    @GetMapping("/home/{clientId}")
    public String clientHome(@PathVariable Long clientId, Model model) {
        Client client = clientService.findById(clientId);
        model.addAttribute("client", client);
        return "client/home";
    }
}
