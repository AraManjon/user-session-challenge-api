package com.usersession.controllers.response;

import java.util.Date;
import java.util.Objects;

/**
 * this class contains data from userSession
 */

public class UserDataResponse {

  private final String username;
  private final String token;
  private final Date date;

  public UserDataResponse(String username, String token, Date date) {
    this.username = username;
    this.token = token;
    this.date = date;
  }

  public String getUsername() {
    return username;
  }

  public String getToken() {
    return token;
  }

  public Date getDate() {
    return date;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserDataResponse that = (UserDataResponse) o;
    return Objects.equals(username, that.username) &&
        Objects.equals(token, that.token) &&
        Objects.equals(date, that.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, token, date);
  }
}
