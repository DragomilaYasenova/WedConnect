package com.wed_connect.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "wedding")
public class Wedding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String nameBride;

    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String nameGroom;

    @Pattern(regexp = "\\+?[0-9\\-\\s]+", message = "Invalid phone number format")
    private String phoneNumberBride;

    @Pattern(regexp = "\\+?[0-9\\-\\s]+", message = "Invalid phone number format")
    private String phoneNumberGroom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Wedding date cannot be in the past.")
    private LocalDate dateWedding;

    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Address can only contain letters and spaces")
    private String location;

    @Min(value = 0, message = "Can't be negative.")
    @Max(value = 3000, message = "The number of guests cannot exceed 3000.")
    private int numberOfGuests;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "wedding_agency_id")
    private WeddingAgency weddingAgency;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "wedding")
    private List<GuestWedding> guestWeddings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameBride() {
        return nameBride;
    }

    public void setNameBride(String nameBride) {
        this.nameBride = nameBride;
    }

    public String getNameGroom() {
        return nameGroom;
    }

    public void setNameGroom(String nameGroom) {
        this.nameGroom = nameGroom;
    }

    public String getPhoneNumberBride() {
        return phoneNumberBride;
    }

    public void setPhoneNumberBride(String phoneNumberBride) {
        this.phoneNumberBride = phoneNumberBride;
    }

    public String getPhoneNumberGroom() {
        return phoneNumberGroom;
    }

    public void setPhoneNumberGroom(String phoneNumberGroom) {
        this.phoneNumberGroom = phoneNumberGroom;
    }

    public LocalDate getDateWedding() {
        return dateWedding;
    }

    public void setDateWedding(LocalDate dateWedding) {
        this.dateWedding = dateWedding;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public WeddingAgency getWeddingAgency() {
        return weddingAgency;
    }

    public void setWeddingAgency(WeddingAgency weddingAgency) {
        this.weddingAgency = weddingAgency;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<GuestWedding> getGuestWeddings() {
        return guestWeddings;
    }

    public void setGuestWeddings(List<GuestWedding> guestWeddings) {
        this.guestWeddings = guestWeddings;
    }
}
