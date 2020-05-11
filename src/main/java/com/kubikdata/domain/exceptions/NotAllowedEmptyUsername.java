package com.kubikdata.domain.exceptions;

public class NotAllowedEmptyUsername extends SessionException {
  public NotAllowedEmptyUsername(String message) {
    super(message);
  }
}
