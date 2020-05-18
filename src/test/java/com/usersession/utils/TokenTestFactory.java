package com.usersession.utils;

import com.usersession.services.TokenUsernameGenerator;

public class TokenTestFactory {
  public static String createBy(String username) {
    TokenUsernameGenerator generator = new TokenUsernameGenerator();
    return generator.code(username);
  }
}
