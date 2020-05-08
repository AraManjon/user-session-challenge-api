package com.kubikdata.services;

import org.springframework.stereotype.Component;

@Component
public class TokenUsernameGenerator implements TokenGenerator {

  @Override
  public String code(String username) {
    return "thisIsAToken";
  }

  public String decode(String username) {
    return null;
  }
}
