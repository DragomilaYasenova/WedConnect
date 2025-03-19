package com.wed_connect.backend.repository;

import com.wed_connect.backend.model.Wedding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeddingRepository extends JpaRepository<Wedding, Long> {
    boolean existsByClientId(Long clientId);
    Optional<Wedding> findByClientId(Long clientId);
}
