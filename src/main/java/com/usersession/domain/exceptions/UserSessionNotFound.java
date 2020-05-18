package com.usersession.domain.exceptions;

public class UserSessionNotFound extends RuntimeException {

  public UserSessionNotFound(String message) {
    super(message);
  }
}
