package com.wed_connect.backend.exception.client;

public class ClientValidationException extends RuntimeException {
  public ClientValidationException(String message) {
    super(message);
  }
}
