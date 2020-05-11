package com.kubikdata.domain.dto;

import java.util.Date;
import java.util.Objects;

public interface DTO {
  class UserSession{

    public String username;
    public String token;
    public Date date;

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      UserSession that = (UserSession) o;
      return Objects.equals(username, that.username) &&
          Objects.equals(token, that.token) &&
          Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
      return Objects.hash(username, token, date);
    }
  }
}
