package com.kubikdata.unit;

import com.kubikdata.services.TokenUsernameGenerator;

public class TokenTestFactory {
  public static String createBy(String username) {
    TokenUsernameGenerator generator = new TokenUsernameGenerator();
    return generator.code(username);
  }
}
