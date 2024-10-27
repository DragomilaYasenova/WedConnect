package com.example.wed_connect.registration.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table (name = "wedding")
public class Wedding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameBride;

    private String nameGroom;

    private String phoneNumberBride;

    private String phoneNumberGroom;

    private Date dateWedding;

    private String location;

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

    public Date getDateWedding() {
        return dateWedding;
    }

    public void setDateWedding(Date dateWedding) {
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
}
