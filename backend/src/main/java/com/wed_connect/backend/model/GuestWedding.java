package com.wed_connect.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class GuestWedding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "wedding_id")
    private Wedding wedding;

    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String nickname;

    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String lastNameNick;

    @Enumerated(EnumType.STRING)
    private FamilyRole familyRole;

    @Enumerated(EnumType.STRING)
    private FamilySide familySide;

    @Enumerated(EnumType.STRING)
    private RSVPStatus rsvpStatus;

    private Integer tableNumber;

    @Enumerated(EnumType.STRING)
    private MenuOptions menuChoice;

    @Size(min = 2, max = 50, message = "Guest group name must be between 2 and 50 characters")
    private String guestGroup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Wedding getWedding() {
        return wedding;
    }

    public void setWedding(Wedding wedding) {
        this.wedding = wedding;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLastNameNick() {
        return lastNameNick;
    }

    public void setLastNameNick(String lastNameNick) {
        this.lastNameNick = lastNameNick;
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

    public RSVPStatus getRsvpStatus() {
        return rsvpStatus;
    }

    public void setRsvpStatus(RSVPStatus rsvpStatus) {
        this.rsvpStatus = rsvpStatus;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public MenuOptions getMenuChoice() {
        return menuChoice;
    }

    public void setMenuChoice(MenuOptions menuChoice) {
        this.menuChoice = menuChoice;
    }

    public String getGuestGroup() {
        return guestGroup;
    }

    public void setGuestGroup(String guestGroup) {
        this.guestGroup = guestGroup;
    }


    @Override
    public String toString() {
        return "GuestWedding{" +
                "id=" + id +
                ", guest=" + guest +
                ", wedding=" + wedding +
                ", nickname='" + nickname + '\'' +
                ", lastNameNick='" + lastNameNick + '\'' +
                ", familyRole=" + familyRole +
                ", familySide=" + familySide +
                ", rsvpStatus=" + rsvpStatus +
                ", tableNumber=" + tableNumber +
                ", menuChoice=" + menuChoice +
                ", guestGroup='" + guestGroup + '\'' +
                '}';
    }
}
