package com.wed_connect.backend.service;

import com.wed_connect.backend.model.Guest;
import com.wed_connect.backend.repository.GuestRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public Guest findGuestById(Long guestId) {
        return guestRepository.findById(guestId)
                .orElseThrow(() -> new RuntimeException("Guest not found with ID: " + guestId));
    }

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public Guest saveGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    public void updateGuest(Guest guest) {
        guestRepository.save(guest);
    }

    public void deleteGuest(Long guestId) {
        guestRepository.deleteById(guestId);
    }
}
