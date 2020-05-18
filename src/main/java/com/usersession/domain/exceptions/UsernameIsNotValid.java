package com.usersession.domain.exceptions;

public class UsernameIsNotValid extends UserSessionBadRequest {

  public UsernameIsNotValid(String message) {
    super(message);
  }
}
