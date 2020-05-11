package com.kubikdata.domain.exceptions;

public class NotAllowedEmptyUsername extends UserSessionException {

  public NotAllowedEmptyUsername(String message) {
    super(message);
  }
}
