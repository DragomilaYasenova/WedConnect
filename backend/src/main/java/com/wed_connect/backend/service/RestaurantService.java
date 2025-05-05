package com.wed_connect.backend.service;

import com.wed_connect.backend.dto.RestaurantDTO;
import com.wed_connect.backend.exception.RestaurantNotFoundException;
import com.wed_connect.backend.model.Restaurant;
import com.wed_connect.backend.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserService userService;

    public RestaurantService(RestaurantRepository restaurantRepository, UserService userService) {
        this.restaurantRepository = restaurantRepository;
        this.userService = userService;
    }

    public Restaurant findById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with ID: " + restaurantId));
    }

    public Restaurant findByUserId(Long userId) {
        return restaurantRepository.findByUserId(userId)
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found for user ID: " + userId));
    }

    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(this::convertToRestaurantDTO)
                .collect(Collectors.toList());
    }

    public Long getRestaurantIdByUsername(String username) {
        Long userId = userService.returnUserIdByUsername(username);
        return restaurantRepository.findByUserId(userId)
                .map(Restaurant::getId)
                .orElseThrow(() -> new RestaurantNotFoundException("No restaurant found for username: " + username));
    }

    @Transactional
    public void updateRestaurantProfile(Long restaurantId, RestaurantDTO restaurantDTO) {
        Restaurant restaurant = findById(restaurantId);
        restaurant.setName(restaurantDTO.getName());
        restaurant.setPhoneNumber(restaurantDTO.getPhoneNumber());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setCapacity(restaurantDTO.getCapacity());
        restaurantRepository.save(restaurant);
    }

    public RestaurantDTO getRestaurantDTOById(Long restaurantId) {
        Restaurant restaurant = findById(restaurantId);
        return convertToRestaurantDTO(restaurant);
    }

    private RestaurantDTO convertToRestaurantDTO(Restaurant restaurant) {
        if (restaurant == null) return null;
        return new RestaurantDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getPhoneNumber(),
                restaurant.getAddress(),
                restaurant.getCapacity()
        );
    }
}