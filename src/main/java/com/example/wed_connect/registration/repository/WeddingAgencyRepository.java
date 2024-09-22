package com.example.wed_connect.registration.repository;

import com.example.wed_connect.registration.model.WeddingAgency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeddingAgencyRepository extends JpaRepository<WeddingAgency, String> {
    boolean existsByUserId(Long userId);
    Optional<WeddingAgency> findByUserId(Long userId);
}
