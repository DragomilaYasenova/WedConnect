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

import java.time.LocalDate;

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
        LocalDate originalDate = wedding.getDateWedding();
        Restaurant originalRestaurant = wedding.getRestaurant();

        wedding.setNameBride(weddingDTO.getNameBride());
        wedding.setNameGroom(weddingDTO.getNameGroom());
        wedding.setPhoneNumberBride(weddingDTO.getPhoneNumberBride());
        wedding.setPhoneNumberGroom(weddingDTO.getPhoneNumberGroom());
        wedding.setDateWedding(weddingDTO.getDateWedding());
        wedding.setLocation(weddingDTO.getLocation());
        wedding.setNumberOfGuests(weddingDTO.getNumberOfGuests());

        if (weddingDTO.getRestaurantId() != null) {
            Restaurant newRestaurant = restaurantService.findById(weddingDTO.getRestaurantId());

            boolean isChangingRestaurant = originalRestaurant == null ||
                    !originalRestaurant.getId().equals(newRestaurant.getId());
            boolean isChangingDate = !originalDate.equals(wedding.getDateWedding());

            if (isChangingRestaurant || isChangingDate) {
                if (originalRestaurant != null) {
                    originalRestaurant.getBookedDates().remove(originalDate);
                    restaurantRepository.save(originalRestaurant);
                }

                if (newRestaurant.getBookedDates().contains(wedding.getDateWedding())) {
                    throw new IllegalStateException("Restaurant is already booked for the selected date");
                }

                newRestaurant.getBookedDates().add(wedding.getDateWedding());
                restaurantRepository.save(newRestaurant);
                wedding.setRestaurant(newRestaurant);
            }
        } else {
            if (originalRestaurant != null) {
                originalRestaurant.getBookedDates().remove(originalDate);
                restaurantRepository.save(originalRestaurant);
                wedding.setRestaurant(null);
            }
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
        Restaurant newRestaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));

        if (newRestaurant.getBookedDates().contains(wedding.getDateWedding())) {
            throw new IllegalStateException("Restaurant is already booked for the selected date");
        }

        if (wedding.getRestaurant() != null && !wedding.getRestaurant().getId().equals(restaurantId)) {
            wedding.getRestaurant().getBookedDates().remove(wedding.getDateWedding());
            restaurantRepository.save(wedding.getRestaurant());
        }

        newRestaurant.getBookedDates().add(wedding.getDateWedding());
        restaurantRepository.save(newRestaurant);

        wedding.setRestaurant(newRestaurant);
        weddingRepository.save(wedding);
    }

    @Transactional
    public void removeRestaurantFromWedding(Long weddingId) {
        Wedding wedding = weddingRepository.findById(weddingId)
                .orElseThrow(() -> new EntityNotFoundException("Wedding not found"));

        if (wedding.getRestaurant() != null) {
            wedding.getRestaurant().getBookedDates().remove(wedding.getDateWedding());
            restaurantRepository.save(wedding.getRestaurant());
            wedding.setRestaurant(null);
            weddingRepository.save(wedding);
        }
    }

}
