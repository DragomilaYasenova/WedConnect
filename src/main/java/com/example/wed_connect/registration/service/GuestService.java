package com.example.wed_connect.registration.service;

import com.example.wed_connect.registration.model.*;
import com.example.wed_connect.registration.repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public Guest createGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public List<Guest> getGuestsByWeddingId(Long weddingId) {
        return guestRepository.findByWeddingId(weddingId);
    }

    public Guest findById(Long guestId) {
        Optional<Guest> guest = guestRepository.findById(guestId);

        if (guest.isEmpty()) {
            throw new RuntimeException("Guest not found with ID: " + guestId);
        }
        return guest.get();
    }

    public void updateGuest(Long guestId, String firstName, String lastName, String email, String gender,
                            String tableNumber, MenuOptions menu, FamilyRole familyRole, FamilySide familySide,
                            GuestGroup guestGroup, Wedding wedding, User user) {
        Guest guest = findById(guestId);
        guest.setFirstName(firstName);
        guest.setLastName(lastName);
        guest.setEmail(email);
        guest.setGender(gender);
        guest.setTableNumber(tableNumber);
        guest.setMenu(menu);
        guest.setFamilyRole(familyRole);
        guest.setFamilySide(familySide);
        guest.setGuestGroup(guestGroup);
        guest.setWedding(wedding);
        guest.setUser(user);

        guestRepository.save(guest);
    }

}
