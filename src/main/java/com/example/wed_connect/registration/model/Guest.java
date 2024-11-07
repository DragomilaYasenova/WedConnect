package com.example.wed_connect.registration.model;

import jakarta.persistence.*;

@Entity
@Table (name = "guest")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String gender;

    private String tableNumber;

    @Enumerated(EnumType.STRING)
    private MenuOptions menu;

    @Enumerated(EnumType.STRING)
    private FamilyRole familyRole;

    @Enumerated(EnumType.STRING)
    private FamilySide familySide;

    @ManyToOne
    @JoinColumn(name = "guest_group_id")
    private GuestGroup guestGroup;

    @ManyToOne
    @JoinColumn(name = "wedding_id", nullable = false)
    private Wedding wedding;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
    private RSVP rsvp;



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

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public MenuOptions getMenu() {
        return menu;
    }

    public void setMenu(MenuOptions menu) {
        this.menu = menu;
    }

    public FamilyRole getFamilyRole() {
        return familyRole;
    }

    public void setFamilyRole(FamilyRole familyRole) {
        this.familyRole = familyRole;
    }

    public FamilySide getFamilySide() {
        return familySide;
    }

    public void setFamilySide(FamilySide familySide) {
        this.familySide = familySide;
    }

    public GuestGroup getGuestGroup() {
        return guestGroup;
    }

    public void setGuestGroup(GuestGroup guestGroup) {
        this.guestGroup = guestGroup;
    }

    public Wedding getWedding() {
        return wedding;
    }

    public void setWedding(Wedding wedding) {
        this.wedding = wedding;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RSVP getRsvp() {
        return rsvp;
    }

    public void setRsvp(RSVP rsvp) {
        this.rsvp = rsvp;
    }
}
