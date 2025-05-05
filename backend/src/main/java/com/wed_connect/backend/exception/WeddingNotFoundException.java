package com.wed_connect.backend.exception;

public class WeddingNotFoundException extends RuntimeException {
    public WeddingNotFoundException(String message) {
        super(message);
    }
}