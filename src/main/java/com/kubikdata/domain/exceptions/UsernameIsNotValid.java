package com.kubikdata.domain.exceptions;

public class UsernameIsNotValid extends RuntimeException {

  public UsernameIsNotValid(String message) {
    super(message);
  }
}
