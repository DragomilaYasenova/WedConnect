package com.wed_connect.backend.repository;

import com.wed_connect.backend.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
    boolean existsByUserId(Long userId);
    Optional<Restaurant> findByUserId(Long userId);
}
