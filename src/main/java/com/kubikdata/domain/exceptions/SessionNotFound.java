package com.kubikdata.domain.exceptions;

public class SessionNotFound extends RuntimeException {
  public SessionNotFound(String message) {
    super(message);
  }
}
