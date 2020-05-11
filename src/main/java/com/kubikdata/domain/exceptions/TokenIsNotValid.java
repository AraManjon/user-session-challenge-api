package com.kubikdata.domain.exceptions;

public class TokenIsNotValid extends RuntimeException {

  public TokenIsNotValid(String message) {
    super(message);
  }
}
