package com.example.wed_connect.registration.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "guest_group")
public class GuestGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName;

    @OneToMany(mappedBy = "guestGroup")
    private List<Guest> members = new ArrayList<>();
}
