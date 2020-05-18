package com.usersession.domain.infrastructure;

public interface TokenGenerator {

  String code(String username);

  Boolean decode(String token, String username);
}
