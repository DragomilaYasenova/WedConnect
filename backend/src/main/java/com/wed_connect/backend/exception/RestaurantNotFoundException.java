package com.wed_connect.backend.exception;

public class RestaurantNotFoundException extends RuntimeException {
  public RestaurantNotFoundException(String message) {
    super(message);
  }
}
