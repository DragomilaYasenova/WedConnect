package com.wed_connect.backend.repository;

import com.wed_connect.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>{
    boolean existsByUsername(String username);
    boolean existsByPassword(String password);

    @Query("SELECT u.id FROM User u WHERE u.username = :username")
    Long findIdByUsername(@Param("username") String username);

    Optional<User> findByUsername(String username);
}
