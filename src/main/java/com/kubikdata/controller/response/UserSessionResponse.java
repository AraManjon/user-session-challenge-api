package com.kubikdata.controller.response;

import java.util.Objects;

public class UserSessionResponse {
  private final String token;

  public UserSessionResponse(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserSessionResponse that = (UserSessionResponse) o;
    return Objects.equals(token, that.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token);
  }
}
