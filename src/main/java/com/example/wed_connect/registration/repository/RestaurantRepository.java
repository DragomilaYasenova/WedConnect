package com.example.wed_connect.registration.repository;

import com.example.wed_connect.registration.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
}
