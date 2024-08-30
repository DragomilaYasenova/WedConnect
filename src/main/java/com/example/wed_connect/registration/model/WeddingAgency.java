package com.example.wed_connect.registration.model;

import jakarta.persistence.*;

@Entity
@Table(name = "wedding_agency")
public class WeddingAgency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phoneNumber;

    private String address;

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
