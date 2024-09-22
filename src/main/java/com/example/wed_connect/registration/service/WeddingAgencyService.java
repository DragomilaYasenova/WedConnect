package com.example.wed_connect.registration.service;

import com.example.wed_connect.registration.model.WeddingAgency;
import com.example.wed_connect.registration.repository.WeddingAgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeddingAgencyService {

    @Autowired
    private WeddingAgencyRepository weddingAgencyRepository;

    public WeddingAgency findById(Long weddingAgencyId) {
        return weddingAgencyRepository.findById(String.valueOf(weddingAgencyId)).orElse(null);
    }

    public WeddingAgency findByUserId(Long userId) {
        return weddingAgencyRepository.findByUserId(userId).orElse(null);
    }

    public WeddingAgency updateWeddingAgencyInfo(Long weddingAgencyId, String name, String phoneNumber, String address, Double max_distance_km) {
        WeddingAgency weddingAgency = findById(weddingAgencyId);
        weddingAgency.setName(name);
        weddingAgency.setPhoneNumber(phoneNumber);
        weddingAgency.setAddress(address);
        weddingAgency.setMaxDistanceKm(max_distance_km);
        return weddingAgencyRepository.save(weddingAgency);
    }
}
