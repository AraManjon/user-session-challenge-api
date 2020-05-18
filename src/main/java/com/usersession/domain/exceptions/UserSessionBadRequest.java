package com.usersession.domain.exceptions;

public class UserSessionBadRequest extends RuntimeException{
  public UserSessionBadRequest(String message) {
    super(message);
  }
}
