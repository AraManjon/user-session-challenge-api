package com.kubikdata.domain.infrastructure;

import io.jsonwebtoken.Claims;

public interface TokenGenerator {

  String code(String username);

  Claims decode(String token);
}
