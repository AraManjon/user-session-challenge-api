package com.kubikdata.services;

import org.springframework.stereotype.Component;

@Component
public class TokenUsernameGenerator implements TokenGenerator {

  @Override
  public String generate(String username) {
    return "thisIsAToken";
  }
}
