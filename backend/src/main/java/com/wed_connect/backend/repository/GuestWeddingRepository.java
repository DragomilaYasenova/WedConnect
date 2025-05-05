package com.wed_connect.backend.repository;

import com.wed_connect.backend.model.GuestWedding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GuestWeddingRepository extends JpaRepository<GuestWedding, Long> {
    List<GuestWedding> findByGuestId(Long guestId);
    Optional<GuestWedding> findByGuestIdAndWeddingId(Long guestId, Long weddingId);
    List<GuestWedding> findByWeddingId(Long weddingId);
}
