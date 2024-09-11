package com.example.wed_connect.registration.controller;

import com.example.wed_connect.registration.model.Client;
import com.example.wed_connect.registration.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client/info")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{clientId}")
    public String showClientInfo(@PathVariable Long clientId, Model model) {
        Client client = clientService.findById(clientId);
        model.addAttribute("client", client);
        return "client/info";
    }

    @PostMapping("/{clientId}")
    public String updateClientInfo(@PathVariable Long clientId, @ModelAttribute Client client) {
        clientService.updateClientInfo(clientId, client.getName(), client.getPhoneNumber());
        return "redirect:/client/home";
    }
}
