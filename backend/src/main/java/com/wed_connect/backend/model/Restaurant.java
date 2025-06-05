package com.wed_connect.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Pattern(regexp = "\\+?[0-9\\-\\s]+", message = "Invalid phone number format")
    private String phoneNumber;

    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Address can only contain letters and spaces")
    private String address;

    private String capacity;

    @ElementCollection
    @CollectionTable(name = "restaurant_booked_dates", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "booked_date")
    private Set<LocalDate> bookedDates = new HashSet<>();

    public void addBookedDate(LocalDate date) {
        this.bookedDates.add(date);
    }

    public void removeBookedDate(LocalDate date) {
        this.bookedDates.remove(date);
    }

    public boolean isDateAvailable(LocalDate date) {
        return !this.bookedDates.contains(date);
    }

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}