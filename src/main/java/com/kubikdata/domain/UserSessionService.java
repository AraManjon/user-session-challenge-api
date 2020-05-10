package com.kubikdata.domain;

import com.kubikdata.controllers.response.UserSessionResponse;
import com.kubikdata.domain.entities.DTO;
import com.kubikdata.domain.entities.Token;
import com.kubikdata.domain.entities.UserSession;
import com.kubikdata.domain.entities.Username;
import com.kubikdata.infrastructure.Repository;
import com.kubikdata.services.TimeServer;
import com.kubikdata.services.TokenGenerator;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserSessionService {
  private final TokenGenerator tokenGenerator;
  private final TimeServer timeServer;
  private final Repository sessionInMemoryRepository;

  public UserSessionService(TokenGenerator tokenGenerator, TimeServer timeServer, Repository sessionInMemoryRepository) {

    this.tokenGenerator = tokenGenerator;
    this.timeServer = timeServer;
    this.sessionInMemoryRepository = sessionInMemoryRepository;
  }

  public UserSessionResponse addSession(Username username) {

    UserSession userSession = createUserSession(username);

    DTO.UserSession userSessionDTO = userSession.createDTO();
    sessionInMemoryRepository.addUser(userSessionDTO);

    UserSessionResponse userSessionResponse = new UserSessionResponse();
    userSessionResponse.setToken(tokenGenerator.code(username.getUsername()));

    return userSessionResponse;
  }

  private UserSession createUserSession(Username username) {
    Date date = timeServer.generate();
    Token token = new Token(tokenGenerator.code(username.getUsername()));
    return new UserSession(username, token, date);
  }
}
