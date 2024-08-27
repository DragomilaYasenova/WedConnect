package com.example.wed_connect.registration.model;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    private String username;

    private String password;

    private transient String confirmPassword;

    @Enumerated(EnumType.STRING)
    private UserType type;

    public User() {
    }

    public User(String username, String password, UserType type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
