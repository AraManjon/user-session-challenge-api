package com.usersession.domain.exceptions;

public class TokenIsNotValid extends UserSessionBadRequest {

  public TokenIsNotValid(String message) {
    super(message);
  }
}
