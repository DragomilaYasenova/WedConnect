package com.wed_connect.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String firstName;

    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String lastName;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 2, max = 50, message = "Gender must be between 2 and 50 characters")
    private String gender;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "guest")
    private List<GuestWedding> guestWeddings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<GuestWedding> getGuestWeddings() {
        return guestWeddings;
    }

    public void setGuestWeddings(List<GuestWedding> guestWeddings) {
        this.guestWeddings = guestWeddings;
    }
}