package com.example.wed_connect.registration.repository;

import com.example.wed_connect.registration.model.Wedding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeddingRepository extends JpaRepository<Wedding, Long> {
    boolean existsByClientId(Long clientId);
    Optional<Wedding> findByClientId(Long clientId);
}
