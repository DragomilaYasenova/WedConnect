package com.example.wed_connect.registration.controller;

import com.example.wed_connect.registration.model.Guest;
import com.example.wed_connect.registration.service.GuestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/client")
public class GuestController {

    private GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping("/guests/{weddingId}")
    public String getAllGuests(Model model) {
        List<Guest> guests = guestService.getAllGuests();
        model.addAttribute("guests", guests);

        return "client/guests";
    }

}
