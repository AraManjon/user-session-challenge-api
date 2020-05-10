package com.kubikdata.domain;

import com.kubikdata.controllers.response.UserSessionResponse;
import com.kubikdata.domain.entities.Username;
import com.kubikdata.infrastructure.Repository;
import com.kubikdata.services.TimeServer;
import com.kubikdata.services.TokenGenerator;
import org.springframework.stereotype.Service;

@Service
public class UserSessionService {
  private final TokenGenerator tokenGenerator;

  public UserSessionService(TokenGenerator tokenGenerator, TimeServer timeServer, Repository sessionInMemoryRepository) {

    this.tokenGenerator = tokenGenerator;
  }

  public UserSessionResponse addSession(Username username) {

    UserSessionResponse userSessionResponse = new UserSessionResponse();
    userSessionResponse.setToken(tokenGenerator.code(username.getUsername()));
    return userSessionResponse;
  }
}
