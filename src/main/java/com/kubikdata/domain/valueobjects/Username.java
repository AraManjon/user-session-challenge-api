package com.kubikdata.domain.valueobjects;

import com.kubikdata.domain.entities.NotAllowedEmptyUsername;

public class Username {

  private final String username;

  public Username(String username) {
    if(username.isEmpty()) throw new NotAllowedEmptyUsername("Username can not be empty");
    this.username = username;
  }

  public String getUsername() {
    return this.username;
  }
}
