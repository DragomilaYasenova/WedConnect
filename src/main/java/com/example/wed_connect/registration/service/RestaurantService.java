package com.example.wed_connect.registration.service;

import com.example.wed_connect.registration.model.Restaurant;
import com.example.wed_connect.registration.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant findById(Long restaurantId) {
        return restaurantRepository.findById(String.valueOf(restaurantId)).orElse(null);
    }

    public Restaurant findByUserId(Long userId) {
        return restaurantRepository.findByUserId(userId).orElse(null);
    }

    public void updateRestaurantInfo(Long restaurantId, String name, String phoneNumber, String address) {
        Restaurant restaurant = findById(restaurantId);
        restaurant.setName(name);
        restaurant.setPhoneNumber(phoneNumber);
        restaurant.setAddress(address);
        restaurantRepository.save(restaurant);
    }


}
