package com.usersession.domain.valueobjects;

import com.usersession.domain.exceptions.UsernameIsNotValid;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Username {

  private final String username;
  private final Pattern VALID_USERNAME_REGEX = Pattern.compile("^(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$");

  public Username(String username) {
    if(!validate(username)) throw new UsernameIsNotValid("Username not valid");

    this.username = username;
  }

  public String getUsername() {
    return this.username;
  }

  private boolean validate(String username) {
    Matcher matcher = VALID_USERNAME_REGEX.matcher(username);
    return matcher.find();
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
