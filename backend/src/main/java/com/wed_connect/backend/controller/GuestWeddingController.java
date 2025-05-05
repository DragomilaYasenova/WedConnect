package com.wed_connect.backend.controller;

import com.wed_connect.backend.model.*;
import com.wed_connect.backend.service.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/client")
public class GuestWeddingController {

    private final GuestWeddingService guestWeddingService;
    private final WeddingService weddingService;
    private final UserService userService;
    private final ClientService clientService;
    private final GuestService guestService;

    public GuestWeddingController(GuestWeddingService guestWeddingService, WeddingService weddingService,
                                  UserService userService, ClientService clientService, GuestService guestService) {
        this.guestWeddingService = guestWeddingService;
        this.weddingService = weddingService;
        this.userService = userService;
        this.clientService = clientService;
        this.guestService = guestService;
    }

    @GetMapping("/guests")
    public String getAllGuests(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Long weddingId = getWeddingIdFromUser(userDetails);

        model.addAttribute("sortedGuests", guestWeddingService.getGuestWeddingsSortedByName(weddingId));
        model.addAttribute("groupedByTable", guestWeddingService.getGuestWeddingsGroupedByTable(weddingId));
        model.addAttribute("guestWeddings", guestWeddingService.getGuestWeddingsByWeddingId(weddingId));

        model.addAttribute("familyRole", FamilyRole.values());
        model.addAttribute("familySide", FamilySide.values());
        model.addAttribute("rsvpStatus", RSVPStatus.values());
        model.addAttribute("menuChoice", MenuOptions.values());

        return "client/guests";
    }

    @PostMapping("/guests")
    public String saveOrUpdateGuestForWedding(@AuthenticationPrincipal UserDetails userDetails,
                                              @ModelAttribute Guest guest,
                                              @ModelAttribute GuestWedding guestWedding) {
        Long weddingId = getWeddingIdFromUser(userDetails);

        if (guestWedding.getId() != null) {
            guestService.updateGuest(guest);
            guestWeddingService.updateGuestWedding(guestWedding);
        } else {
            Guest savedGuest = guestService.saveGuest(guest);
            guestWeddingService.addGuestToWedding(savedGuest, guestWedding, weddingId);
            guestWeddingService.updateGuestWedding(guestWedding);
        }

        return "redirect:/client/guests";
    }

    @PostMapping("/guests/edit")
    public String updateGuestForWedding(@ModelAttribute GuestWedding guestWedding) {
        guestWeddingService.updateGuestWedding(guestWedding);
        return "redirect:/client/guests";
    }

    @GetMapping("/guests/edit")
    public String editGuest(@RequestParam("guestWeddingId") Long guestWeddingId, Model model) {
        GuestWedding guestWedding = guestWeddingService.findById(guestWeddingId)
                .orElseThrow(() -> new RuntimeException("Guest Wedding not found."));

        model.addAttribute("guest", guestWedding.getGuest());
        model.addAttribute("guestWedding", guestWedding);
        model.addAttribute("familyRole", FamilyRole.values());
        model.addAttribute("familySide", FamilySide.values());
        model.addAttribute("rsvpStatus", RSVPStatus.values());
        model.addAttribute("menuChoice", MenuOptions.values());

        return "client/edit-guest";
    }

    @PostMapping("/guests/delete")
    public String deleteGuest(@RequestParam("guestWeddingId") Long guestWeddingId) {
        guestWeddingService.deleteGuestWedding(guestWeddingId);
        return "redirect:/client/guests";
    }

    private Long getWeddingIdFromUser(UserDetails userDetails) {
        return weddingService.findByClientId(clientService.getClientIdByUsername(userDetails.getUsername())).getId();
    }
}
