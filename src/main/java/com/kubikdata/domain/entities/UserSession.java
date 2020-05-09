package com.kubikdata.domain.entities;

import java.util.Objects;

public class UserSession {
  private final Username username;
  private final Token token;

  public UserSession(Username username, Token token) {

    this.username = username;
    this.token = token;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserSession that = (UserSession) o;
    return Objects.equals(username, that.username) &&
        Objects.equals(token, that.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, token);
  }
}
