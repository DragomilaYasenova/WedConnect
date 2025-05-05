package com.wed_connect.backend.service;

import com.wed_connect.backend.model.*;
import com.wed_connect.backend.repository.GuestRepository;
import com.wed_connect.backend.repository.GuestWeddingRepository;
import com.wed_connect.backend.repository.WeddingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GuestWeddingService {
    private final GuestWeddingRepository guestWeddingRepository;
    private final WeddingRepository weddingRepository;
    private final GuestRepository guestRepository;


    public GuestWeddingService(GuestWeddingRepository guestWeddingRepository, WeddingRepository weddingRepository, GuestRepository guestRepository) {
        this.guestWeddingRepository = guestWeddingRepository;
        this.weddingRepository = weddingRepository;
        this.guestRepository = guestRepository;

    }

    public List<GuestWedding> getGuestWeddingsByWeddingId(Long weddingId) {
        return guestWeddingRepository.findByWeddingId(weddingId);
    }

    public List<GuestWedding> getGuestWeddingsSortedByName(Long weddingId) {
        return guestWeddingRepository.findByWeddingId(weddingId).stream()
                .sorted((g1, g2) -> g1.getNickname().compareToIgnoreCase(g2.getNickname()))
                .collect(Collectors.toList());
    }

    public Map<Integer, List<GuestWedding>> getGuestWeddingsGroupedByTable(Long weddingId) {
        return guestWeddingRepository.findByWeddingId(weddingId).stream()
                .collect(Collectors.groupingBy(GuestWedding::getTableNumber));
    }

    @Transactional
    public void addGuestToWedding(Guest guest, GuestWedding guestWedding, Long weddingId) {
        Guest existingGuest = guestRepository.findById(guest.getId())
                .orElseThrow(() -> new RuntimeException("Guest not found with ID: " + guest.getId()));
        Wedding wedding = weddingRepository.findById(weddingId)
                .orElseThrow(() -> new RuntimeException("Wedding not found with ID: " + weddingId));

        guestWedding.setGuest(existingGuest);
        guestWedding.setWedding(wedding);
        guestWedding.setNickname(existingGuest.getFirstName());
        guestWedding.setLastNameNick(existingGuest.getLastName());

        guestWeddingRepository.save(guestWedding);
    }

    @Transactional
    public void updateGuestWedding(GuestWedding guestWedding) {
        if (guestWedding.getGuest() == null || guestWedding.getGuest().getId() == null) {
            throw new RuntimeException("Guest is null or has no ID.");
        }
        if (guestWedding.getWedding() == null || guestWedding.getWedding().getId() == null) {
            throw new RuntimeException("Wedding is null or has no ID.");
        }

        GuestWedding existingGuestWedding = guestWeddingRepository.findByGuestIdAndWeddingId(
                guestWedding.getGuest().getId(),
                guestWedding.getWedding().getId()
        ).orElseThrow(() -> new RuntimeException(
                "GuestWedding not found for Guest ID: " + guestWedding.getGuest().getId() +
                        " and Wedding ID: " + guestWedding.getWedding().getId()
        ));

        existingGuestWedding.setNickname(guestWedding.getNickname());
        existingGuestWedding.setLastNameNick(guestWedding.getLastNameNick());
        existingGuestWedding.setFamilyRole(guestWedding.getFamilyRole());
        existingGuestWedding.setFamilySide(guestWedding.getFamilySide());
        existingGuestWedding.setRsvpStatus(guestWedding.getRsvpStatus());
        existingGuestWedding.setTableNumber(guestWedding.getTableNumber());
        existingGuestWedding.setMenuChoice(guestWedding.getMenuChoice());
        existingGuestWedding.setGuestGroup(guestWedding.getGuestGroup());

        guestWeddingRepository.save(existingGuestWedding);
    }


    @Transactional
    public void deleteGuestWedding(Long guestWeddingId) {
        guestWeddingRepository.deleteById(guestWeddingId);
    }

    public Optional<GuestWedding> findById(Long id) {
        return guestWeddingRepository.findById(id);
    }
}
