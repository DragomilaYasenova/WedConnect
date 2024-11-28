package com.example.wed_connect.registration.service;

import com.example.wed_connect.registration.model.*;
import com.example.wed_connect.registration.repository.GuestRepository;
import com.example.wed_connect.registration.repository.GuestWeddingRepository;
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

    public List<GuestDTO> getGuestsByWeddingId(Long weddingId) {
        List<GuestWedding> guestWeddings = guestWeddingRepository.findByWeddingId(weddingId);

        List<GuestDTO> guestDTOs = new ArrayList<>();
        for (GuestWedding guestWedding : guestWeddings) {
            Guest guest = guestWedding.getGuest();
            GuestDTO guestDTO = new GuestDTO(
                    guest.getId(),
                    guest.getFirstName(),
                    guest.getLastName(),
                    guest.getEmail(),
                    guest.getGender()
            );
            guestDTOs.add(guestDTO);
        }

        return guestDTOs;
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


    public List<GuestDTO> getAllGuestDTOs() {
        List<Guest> guests = guestRepository.findAll();
        return guests.stream()
                .map(guest -> new GuestDTO(
                        guest.getId(),
                        guest.getFirstName(),
                        guest.getLastName(),
                        guest.getEmail(),
                        guest.getGender()
                ))
                .collect(Collectors.toList());
    }
}
