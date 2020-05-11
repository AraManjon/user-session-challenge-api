package com.kubikdata.domain.exceptions;

public class NotAllowedEmptyUsername extends RuntimeException {
  public NotAllowedEmptyUsername(String message) {
    super(message);
  }
}
