package com.wed_connect.backend.service;

import com.wed_connect.backend.model.WeddingAgency;
import com.wed_connect.backend.repository.WeddingAgencyRepository;
import org.springframework.stereotype.Service;

@Service
public class WeddingAgencyService {

    private final WeddingAgencyRepository weddingAgencyRepository;

    public WeddingAgencyService(WeddingAgencyRepository weddingAgencyRepository) {
        this.weddingAgencyRepository = weddingAgencyRepository;
    }

    public WeddingAgency findById(Long weddingAgencyId) {
        return weddingAgencyRepository.findById(String.valueOf(weddingAgencyId)).orElse(null);
    }

    public WeddingAgency findByUserId(Long userId) {
        return weddingAgencyRepository.findByUserId(userId).orElse(null);
    }

    public void updateWeddingAgencyInfo(Long weddingAgencyId, String name, String phoneNumber, String address, Double max_distance_km) {
        WeddingAgency weddingAgency = findById(weddingAgencyId);
        weddingAgency.setName(name);
        weddingAgency.setPhoneNumber(phoneNumber);
        weddingAgency.setAddress(address);
        weddingAgency.setMaxDistanceKm(max_distance_km);
        weddingAgencyRepository.save(weddingAgency);
    }
}
