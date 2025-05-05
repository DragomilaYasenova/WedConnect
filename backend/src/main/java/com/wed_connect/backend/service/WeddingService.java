package com.wed_connect.backend.service;

import com.wed_connect.backend.dto.WeddingDTO;
import com.wed_connect.backend.exception.WeddingNotFoundException;
import com.wed_connect.backend.model.Restaurant;
import com.wed_connect.backend.model.Wedding;
import com.wed_connect.backend.repository.RestaurantRepository;
import com.wed_connect.backend.repository.WeddingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WeddingService {

    private final WeddingRepository weddingRepository;
    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;


    public WeddingService(WeddingRepository weddingRepository, RestaurantService restaurantService, RestaurantRepository restaurantRepository) {
        this.weddingRepository = weddingRepository;
        this.restaurantService = restaurantService;
        this.restaurantRepository = restaurantRepository;
    }

    public Wedding findByClientId(Long clientId) {
        return weddingRepository.findByClientId(clientId)
                .orElseThrow(() -> new WeddingNotFoundException("Wedding not found for client ID: " + clientId));
    }

    @Transactional
    public void updateWedding(Long clientId, WeddingDTO weddingDTO) {
        Wedding wedding = findByClientId(clientId);
        wedding.setNameBride(weddingDTO.getNameBride());
        wedding.setNameGroom(weddingDTO.getNameGroom());
        wedding.setPhoneNumberBride(weddingDTO.getPhoneNumberBride());
        wedding.setPhoneNumberGroom(weddingDTO.getPhoneNumberGroom());
        wedding.setDateWedding(weddingDTO.getDateWedding());
        wedding.setLocation(weddingDTO.getLocation());
        wedding.setNumberOfGuests(weddingDTO.getNumberOfGuests());

        if (weddingDTO.getRestaurantId() != null) {
            Restaurant restaurant = restaurantService.findById(weddingDTO.getRestaurantId());
            wedding.setRestaurant(restaurant);
        } else {
            wedding.setRestaurant(null);
        }

        weddingRepository.save(wedding);
    }

    public WeddingDTO getWeddingDTOByClientId(Long clientId) {
        Wedding wedding = findByClientId(clientId);
        return convertToWeddingDTO(wedding);
    }

    private WeddingDTO convertToWeddingDTO(Wedding wedding) {
        if (wedding == null) return null;
        return new WeddingDTO(
                wedding.getId(),
                wedding.getNameBride(),
                wedding.getNameGroom(),
                wedding.getPhoneNumberBride(),
                wedding.getPhoneNumberGroom(),
                wedding.getDateWedding(),
                wedding.getLocation(),
                wedding.getNumberOfGuests(),
                wedding.getRestaurant() != null ? wedding.getRestaurant().getId() : null
        );
    }

    @Transactional
    public void setRestaurantForWedding(Long clientId, Long restaurantId) {

        Wedding wedding = weddingRepository.findByClientId(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Wedding not found for client"));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        wedding.setRestaurant(restaurant);
        weddingRepository.save(wedding);
    }
}
