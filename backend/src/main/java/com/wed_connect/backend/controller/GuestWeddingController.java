package com.wed_connect.backend.controller;

import com.wed_connect.backend.model.*;
import com.wed_connect.backend.repository.GuestWeddingRepository;
import com.wed_connect.backend.repository.UserRepository;
import com.wed_connect.backend.service.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@Controller
@RequestMapping("/client")
public class GuestWeddingController {
    private final GuestWeddingService guestWeddingService;
    private final WeddingService weddingService;
    private final GuestWeddingRepository guestWeddingRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ClientService clientService;
    private final GuestService guestService;

    public GuestWeddingController(GuestWeddingService guestWeddingService, WeddingService weddingService, GuestWeddingRepository guestWeddingRepository, UserService userService, UserRepository userRepository, ClientService clientService, GuestService guestService) {
        this.guestWeddingService = guestWeddingService;
        this.weddingService = weddingService;
        this.guestWeddingRepository = guestWeddingRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.clientService = clientService;
        this.guestService = guestService;
    }

    @GetMapping("/guests")
    public String getAllGuests(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Long weddingId = getWeddingIdFromUser(userDetails);

        List<GuestWedding> sortedGuests = guestWeddingService.getGuestWeddingsSortedByName(weddingId);
        Map<Integer, List<GuestWedding>> groupedByTable = guestWeddingService.getGuestWeddingsGroupedByTable(weddingId);

        model.addAttribute("sortedGuests", sortedGuests);
        model.addAttribute("groupedByTable", groupedByTable);

        model.addAttribute("guestWeddings", guestWeddingRepository.findByWeddingId(weddingId));
        model.addAttribute("familyRole", FamilyRole.values());
        model.addAttribute("familySide", FamilySide.values());
        model.addAttribute("rsvpStatus", RSVPStatus.values());
        model.addAttribute("menuChoice", MenuOptions.values());

        return "client/guests";
    }



    @PostMapping("/guests")
    public String saveOrUpdateGuestForWedding(@AuthenticationPrincipal UserDetails userDetails,
                                              @ModelAttribute("guest") Guest guest,
                                              @ModelAttribute("guestWedding") GuestWedding guestWedding) {

        Long weddingId = getWeddingIdFromUser(userDetails);

        if (guestWedding.getId() != null) {
            updateExistingGuest(guest);
            updateGuestWedding(guestWedding);

        } else if (guest.getId() == null) {
            createNewGuest(guest, weddingId);
            createGuestWedding(guest, guestWedding, weddingId);
        }

        return "redirect:/client/guests";
    }

    @PostMapping("/guests/edit")
    public String updateGuestForWedding(@AuthenticationPrincipal UserDetails userDetails,
                                            @ModelAttribute Guest guest,
                                            @ModelAttribute GuestWedding guestWedding) {

        Long weddingId = getWeddingIdFromUser(userDetails);

        GuestWedding guestWeddingSaved = guestWeddingRepository.findById(guestWedding.getId()).orElse(null);

        if (guestWeddingSaved.getId() != null) {
            updateGuestWedding(guestWedding);
        }

        return "redirect:/client/guests";
    }

    @GetMapping("/guests/edit")
    public String editGuest(@AuthenticationPrincipal UserDetails userDetails,
                            @RequestParam("guestWeddingId") Long guestWeddingId,
                            Model model) {

        GuestWedding guestWedding = guestWeddingRepository.findById(guestWeddingId).orElse(null);

        if (guestWedding == null) {
            model.addAttribute("errorMessage", "Guest Wedding not found.");
            return "client/guests";
        }

        Guest guest = guestWedding.getGuest();

        if (guest != null) {
            model.addAttribute("guest", guest);
            model.addAttribute("guestWedding", guestWedding);
        } else {
            model.addAttribute("errorMessage", "Guest details not found.");
        }

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

    private void createNewGuest(Guest guest, Long weddingId) {
        Guest savedGuest = guestService.saveGuest(guest);
        guestWeddingService.addGuestToWedding(savedGuest.getId(), weddingId, savedGuest.getFirstName(), savedGuest.getLastName());
    }

    private void updateExistingGuest(Guest guest) {
        guestService.updateGuest(
                guest.getId(),
                guest.getFirstName(),
                guest.getLastName(),
                guest.getEmail(),
                guest.getGender()
        );
    }

    private void updateGuestWedding(GuestWedding guestWedding) {

        guestWeddingService.updateGuestWedding(
                guestWedding.getId(),
                guestWedding.getNickname(),
                guestWedding.getLastNameNick(),
                guestWedding.getFamilyRole(),
                guestWedding.getFamilySide(),
                guestWedding.getRsvpStatus(),
                guestWedding.getTableNumber(),
                guestWedding.getMenuChoice(),
                guestWedding.getGuestGroup());
    }


    private void createGuestWedding(Guest guest, GuestWedding guestWedding, Long weddingId) {
        Optional<GuestWedding> existingGuestWeddingOpt = guestWeddingRepository.findByGuestIdAndWeddingId(guest.getId(), weddingId);
        if (existingGuestWeddingOpt.isPresent()) {
            GuestWedding existingGuestWedding = existingGuestWeddingOpt.get();
            guestWeddingService.createGuestWedding(existingGuestWedding.getId(),
                    guestWedding.getFamilyRole(),
                    guestWedding.getFamilySide(),
                    guestWedding.getRsvpStatus(),
                    guestWedding.getTableNumber(),
                    guestWedding.getMenuChoice(),
                    guestWedding.getGuestGroup());
        }

    }



    private Long getWeddingIdFromUser(UserDetails userDetails) {
        String username = userDetails.getUsername();
        Long userID = userRepository.findIdByUsername(username);
        Client client = clientService.findByUserId(userID);
        return weddingService.findByClientId(client.getId()).getId();
    }
}
