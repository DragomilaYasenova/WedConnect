package com.wed_connect.backend.service;

import com.wed_connect.backend.model.*;
import com.wed_connect.backend.repository.GuestRepository;
import com.wed_connect.backend.repository.GuestWeddingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GuestService {

    private GuestRepository guestRepository;
    private GuestWeddingRepository guestWeddingRepository;

    public GuestService(GuestRepository guestRepository, GuestWeddingRepository guestWeddingRepository) {
        this.guestRepository = guestRepository;
        this.guestWeddingRepository = guestWeddingRepository;
    }

    public Guest findById(Long guestId) {
        Optional<Guest> guest = guestRepository.findById(guestId);

        if (guest.isEmpty()) {
            throw new RuntimeException("Guest not found with ID: " + guestId);
        }
        return guest.get();
    }

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public Optional<Guest> getGuestById(Long guestId) {
        return guestRepository.findById(guestId);
    }

    public Guest saveGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    public void deleteGuest(Long guestId) {
        guestRepository.deleteById(guestId);
    }

    public Guest findGuestByEmail(String email) {
        return guestRepository.findByEmail(email);
    }

    public void updateGuest(Long guestId, String firstName, String lastName,
                            String email, String gender) {
        Guest guest = findById(guestId);
        guest.setFirstName(firstName);
        guest.setLastName(lastName);
        guest.setEmail(email);
        guest.setGender(gender);


        guestRepository.save(guest);
    }

    public void updateGuestFromClient(Long guestId, String email, String gender) {
        Guest guest = findById(guestId);
        guest.setEmail(email);
        guest.setGender(gender);


        guestRepository.save(guest);
    }
}
