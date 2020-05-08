package com.kubikdata.domain;

import com.kubikdata.services.TokenGenerator;
import org.springframework.stereotype.Service;

@Service
public class UserSessionService {
  private final TokenGenerator tokenGenerator;

  public UserSessionService(TokenGenerator tokenGenerator) {

    this.tokenGenerator = tokenGenerator;
  }

  public String createToken(String username) {
    return tokenGenerator.generate(username);
  }

  public String addSession(String username) {
    return createToken(username);
  }
}
