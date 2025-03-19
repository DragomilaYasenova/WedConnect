package com.wed_connect.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "wedding_agency")
public class WeddingAgency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Pattern(regexp = "\\+?[0-9\\-\\s]+", message = "Invalid phone number format")
    private String phoneNumber;

    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Address can only contain letters and spaces")
    private String address;

    @Positive(message = "Maximum distance must be a positive number")
    private Double maxDistanceKm;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getMaxDistanceKm() {
        return maxDistanceKm;
    }

    public void setMaxDistanceKm(Double maxDistanceKm) {
        this.maxDistanceKm = maxDistanceKm;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
