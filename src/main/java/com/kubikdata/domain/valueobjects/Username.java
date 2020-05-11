package com.kubikdata.domain.valueobjects;

import com.kubikdata.domain.exceptions.NotAllowedEmptyUsername;

import java.util.Objects;

public class Username {

  private final String username;

  public Username(String username) {
    if(username.isEmpty()) throw new NotAllowedEmptyUsername("Username can not be empty");
    this.username = username;
  }

  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Username username1 = (Username) o;
    return Objects.equals(username, username1.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username);
  }
}
