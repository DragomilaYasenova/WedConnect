package com.example.wed_connect.registration.service;

import com.example.wed_connect.registration.model.*;
import com.example.wed_connect.registration.repository.GuestRepository;
import com.example.wed_connect.registration.repository.GuestWeddingRepository;
import com.example.wed_connect.registration.repository.WeddingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestWeddingService {
    private GuestWeddingRepository guestWeddingRepository;

    private GuestRepository guestRepository;

    private WeddingRepository weddingRepository;

    public GuestWeddingService(GuestWeddingRepository guestWeddingRepository, GuestRepository guestRepository, WeddingRepository weddingRepository) {
        this.guestWeddingRepository = guestWeddingRepository;
        this.guestRepository = guestRepository;
        this.weddingRepository = weddingRepository;
    }

    public List<GuestWedding> findByGuestId(Long guestId) {
        return guestWeddingRepository.findByGuestId(guestId);
    }

    public void addGuestToWedding(Long guestId, Long weddingId, String firstName, String lastName) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new RuntimeException("Guest not found with ID: " + guestId));
        Wedding wedding = weddingRepository.findById(weddingId)
                .orElseThrow(() -> new RuntimeException("Wedding not found with ID: " + weddingId));

        GuestWedding guestWedding = new GuestWedding();
        guestWedding.setGuest(guest);
        guestWedding.setWedding(wedding);
        guestWedding.setNickname(firstName);
        guestWedding.setLastNameNick(lastName);

        guestWeddingRepository.save(guestWedding);
    }

    public void updateGuestWedding(Long guestWeddingId, String firstName, String lastName,
                                   FamilyRole familyRole, FamilySide familySide,
                                   RSVPStatus rsvpStatus, Integer tableNumber, MenuOptions menuChoice, String guestGroup) {
        GuestWedding guestWedding = guestWeddingRepository.findById(guestWeddingId)
                .orElseThrow(() -> new RuntimeException("GuestWedding not found"));

        guestWedding.setNickname(firstName);
        guestWedding.setLastNameNick(lastName);
        guestWedding.setFamilyRole(familyRole);
        guestWedding.setFamilySide(familySide);
        guestWedding.setRsvpStatus(rsvpStatus);
        guestWedding.setTableNumber(tableNumber);
        guestWedding.setMenuChoice(menuChoice);
        guestWedding.setGuestGroup(guestGroup);

        guestWeddingRepository.save(guestWedding);
    }

    public void removeGuestFromWedding(Long guestId, Long weddingId) {
        GuestWedding guestWedding = guestWeddingRepository.findByGuestIdAndWeddingId(guestId, weddingId)
                .orElseThrow(() -> new RuntimeException("GuestWedding association not found for guest ID: " + guestId + " and wedding ID: " + weddingId));

        guestWeddingRepository.delete(guestWedding);
    }

    public void createGuestWedding(Long guestWeddingId, FamilyRole familyRole, FamilySide familySide, RSVPStatus rsvpStatus, Integer tableNumber, MenuOptions menuChoice, String guestGroup) {
        GuestWedding guestWedding = guestWeddingRepository.findById(guestWeddingId)
                .orElseThrow(() -> new RuntimeException("GuestWedding not found"));

        guestWedding.setFamilyRole(familyRole);
        guestWedding.setFamilySide(familySide);
        guestWedding.setRsvpStatus(rsvpStatus);
        guestWedding.setTableNumber(tableNumber);
        guestWedding.setMenuChoice(menuChoice);
        guestWedding.setGuestGroup(guestGroup);

        guestWeddingRepository.save(guestWedding);

    }
}

