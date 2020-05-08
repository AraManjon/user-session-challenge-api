package com.kubikdata.services;

import io.jsonwebtoken.Claims;

public interface TokenGenerator {
  String code(String parameter);
  Claims decode(String token);
}
