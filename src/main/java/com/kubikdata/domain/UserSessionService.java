package com.kubikdata.domain;

import com.kubikdata.controller.response.UserSessionResponse;
import com.kubikdata.services.TokenGenerator;
import org.springframework.stereotype.Service;

@Service
public class UserSessionService {
  private final TokenGenerator tokenGenerator;

  public UserSessionService(TokenGenerator tokenGenerator) {

    this.tokenGenerator = tokenGenerator;
  }

  public UserSessionResponse addSession(Username username) {
    return new UserSessionResponse(tokenGenerator.code(username.get()));
  }
}
