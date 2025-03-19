package com.wed_connect.backend.repository;

import com.wed_connect.backend.model.WeddingAgency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeddingAgencyRepository extends JpaRepository<WeddingAgency, String> {
    boolean existsByUserId(Long userId);
    Optional<WeddingAgency> findByUserId(Long userId);
}
