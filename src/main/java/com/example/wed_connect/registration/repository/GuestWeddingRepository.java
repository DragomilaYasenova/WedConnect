package com.example.wed_connect.registration.repository;

import com.example.wed_connect.registration.model.GuestWedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GuestWeddingRepository extends JpaRepository<GuestWedding, Long> {
    List<GuestWedding> findByGuestId(Long guestId);
    Optional<GuestWedding> findById(Long guestWeddingId);

    Optional<GuestWedding> findByGuestIdAndWeddingId(Long guestId, Long weddingId);

    List<GuestWedding> findByWeddingId(Long weddingId);

}
