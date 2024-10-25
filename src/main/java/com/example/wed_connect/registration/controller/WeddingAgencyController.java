package com.example.wed_connect.registration.controller;

import com.example.wed_connect.registration.model.WeddingAgency;
import com.example.wed_connect.registration.service.WeddingAgencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wedding_agency/info")
public class WeddingAgencyController {

    private final WeddingAgencyService weddingAgencyService;

    public WeddingAgencyController(WeddingAgencyService weddingAgencyService) {
        this.weddingAgencyService = weddingAgencyService;
    }

    @GetMapping("/{wedding_agencyId}")
    public String showClientInfo(@PathVariable Long wedding_agencyId, Model model) {
        WeddingAgency weddingAgency = weddingAgencyService.findById(wedding_agencyId);
        model.addAttribute("wedding_agency", weddingAgency);
        return "wedding_agency/info";
    }

    @PostMapping("/{wedding_agencyId}")
    public String updateWeddingAgencyInfo(@PathVariable Long wedding_agencyId, @ModelAttribute WeddingAgency weddingAgency) {
        weddingAgencyService.updateWeddingAgencyInfo(wedding_agencyId, weddingAgency.getName(), weddingAgency.getPhoneNumber(), weddingAgency.getAddress(), weddingAgency.getMaxDistanceKm());
        return "redirect:/wedding_agency/home";
    }
}
