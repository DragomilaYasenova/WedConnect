package com.example.wed_connect.registration.repository;

import com.example.wed_connect.registration.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
    boolean existsByUserId(Long userId);
    Optional<Restaurant> findByUserId(Long userId);
}
