package com.kubikdata.domain.valueobjects;

public class Token {
  private final String token;

  public Token(String token) {
    if(token.isEmpty()) throw new RuntimeException("Token can not be empty");
    this.token = token;
  }

  public String getToken() {
    return token;
  }
}
