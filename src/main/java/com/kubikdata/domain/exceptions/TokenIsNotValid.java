package com.kubikdata.domain.exceptions;

public class TokenIsNotValid extends UserSessionBadRequest {

  public TokenIsNotValid(String message) {
    super(message);
  }
}
