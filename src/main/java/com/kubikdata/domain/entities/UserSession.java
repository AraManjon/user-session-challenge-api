package com.kubikdata.domain.entities;

import java.util.Date;
import java.util.Objects;

public class UserSession {
  private final Username username;
  private final Token token;
  private final Date date;

  public UserSession(Username username, Token token, Date date) {

    this.username = username;
    this.token = token;
    this.date = date;
  }

  public DTO.UserSession createDTO() {
    DTO.UserSession userSessionDTO = new DTO.UserSession();
    userSessionDTO.username = this.username.getUsername();
    userSessionDTO.token = this.token.getToken();
    userSessionDTO.date = this.date;
    return userSessionDTO;
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
