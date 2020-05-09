package com.kubikdata.domain;

import com.kubikdata.controllers.response.UserSessionResponse;
import com.kubikdata.services.TokenGenerator;
import org.springframework.stereotype.Service;

@Service
public class UserSessionService {
  private final TokenGenerator tokenGenerator;

  public UserSessionService(TokenGenerator tokenGenerator) {

    this.tokenGenerator = tokenGenerator;
  }

  public UserSessionResponse addSession(Username username) {

    UserSessionResponse userSessionResponse = new UserSessionResponse();
    userSessionResponse.setToken(tokenGenerator.code(username.getUsername()));
    return userSessionResponse;
  }
}
