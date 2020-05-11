package com.kubikdata.domain;

import com.kubikdata.controllers.response.UserSessionResponse;
import com.kubikdata.domain.dto.DTO;
import com.kubikdata.domain.valueobjects.Token;
import com.kubikdata.domain.entities.UserSession;
import com.kubikdata.domain.valueobjects.Username;
import com.kubikdata.domain.infrastructure.Repository;
import com.kubikdata.domain.infrastructure.TimeServer;
import com.kubikdata.domain.infrastructure.TokenGenerator;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * this class create userSession and add to repository
 */
@Service
public class UserSessionService {
  private final TokenGenerator tokenGenerator;
  private final TimeServer timeServer;
  private final Repository repository;

  public UserSessionService(TokenGenerator tokenGenerator, TimeServer timeServer, Repository repository) {

    this.tokenGenerator = tokenGenerator;
    this.timeServer = timeServer;
    this.repository = repository;
  }

  public UserSessionResponse addSession(Username username) {

    UserSession userSession = createUserSession(username);

    DTO.UserSession userSessionDTO = userSession.createDTO();
    repository.add(userSessionDTO);

    UserSessionResponse userSessionResponse = new UserSessionResponse();
    userSessionResponse.setToken(userSessionDTO.token);

    return userSessionResponse;
  }

  private UserSession createUserSession(Username username) {
    Date date = timeServer.generate();
    Token token = new Token(tokenGenerator.code(username.getUsername()));
    return new UserSession(username, token, date);
  }
}
