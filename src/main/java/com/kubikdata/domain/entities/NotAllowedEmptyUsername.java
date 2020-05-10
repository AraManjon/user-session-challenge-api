package com.kubikdata.domain.entities;

public class NotAllowedEmptyUsername extends RuntimeException {
  public NotAllowedEmptyUsername(String message) {
    super(message);
  }
}
