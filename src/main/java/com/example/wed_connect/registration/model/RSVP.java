package com.example.wed_connect.registration.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rsvp")
public class RSVP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RSVPStatus status;

    @OneToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;

}