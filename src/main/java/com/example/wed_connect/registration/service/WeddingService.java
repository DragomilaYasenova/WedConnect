package com.example.wed_connect.registration.service;

import com.example.wed_connect.registration.model.Wedding;
import com.example.wed_connect.registration.repository.WeddingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class WeddingService {

    private final WeddingRepository weddingRepository;

    public WeddingService(WeddingRepository weddingRepository) {
        this.weddingRepository = weddingRepository;
    }

    public Wedding findById(Long weddingId) {
        return weddingRepository.findById(weddingId).orElse(null);
    }

    public Wedding findByClientId(Long clientId) {
        return weddingRepository.findByClientId(clientId).orElse(null);
    }

    public void updateWeddingInfo(Long weddingId, String nameBride, String nameGroom, String phoneNumberBride, String phoneNumberGroom, LocalDate dateWedding, String location, int numberOfGuests) {
        Wedding wedding = findById(weddingId);
        wedding.setNameBride(nameBride);
        wedding.setNameGroom(nameGroom);
        wedding.setPhoneNumberBride(phoneNumberBride);
        wedding.setPhoneNumberGroom(phoneNumberGroom);
        wedding.setDateWedding(dateWedding);
        wedding.setLocation(location);
        wedding.setNumberOfGuests(numberOfGuests);
        weddingRepository.save(wedding);
    }

}
