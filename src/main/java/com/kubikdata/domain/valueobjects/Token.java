package com.kubikdata.domain.valueobjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Token {

  private final String token;
  private final Pattern VALID_TOKEN_REGEX = Pattern.compile("^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$");

  public Token(String token) {
    if(token.isEmpty()) throw new RuntimeException("Token can not be empty");
    if(!validate(token)) throw new RuntimeException("Token not valid");
    this.token = token;
  }

  private boolean validate(String token) {
    Matcher matcher = VALID_TOKEN_REGEX.matcher(token);
    return matcher.find();
  }

  public String getToken() {
    return token;
  }
}
