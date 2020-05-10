package com.kubikdata.domain.valueobjects;

import sun.invoke.empty.Empty;

public class Username {

  private final String username;

  public Username(String username) {
    if(username.isEmpty()) throw new RuntimeException("Username can not be empty");
    this.username = username;
  }

  public String getUsername() {
    return this.username;
  }
}
