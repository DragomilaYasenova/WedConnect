package com.wed_connect.backend.model;

import lombok.Getter;

@Getter
public enum UserType {
    CLIENT("Client"),
    WEDDING_AGENCY("Wedding Agency"),
    RESTAURANT("Restaurant");

    private final String displayName;

    UserType(String displayName) {
        this.displayName = displayName;
    }

}
