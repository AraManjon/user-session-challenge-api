package com.kubikdata.domain;

import com.kubikdata.controllers.response.SessionResponse;
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
  private final Repository sessionRepository;

  public UserSessionService(TokenGenerator tokenGenerator, TimeServer timeServer, Repository sessionRepository) {

    this.tokenGenerator = tokenGenerator;
    this.timeServer = timeServer;
    this.sessionRepository = sessionRepository;
  }

  public SessionResponse addSession(Username username) {

    UserSession userSession = createUserSession(username);

    DTO.UserSession userSessionDTO = userSession.createDTO();
    sessionRepository.add(userSessionDTO);

    SessionResponse sessionResponse = new SessionResponse();
    sessionResponse.setToken(userSessionDTO.token);

    return sessionResponse;
  }

  private UserSession createUserSession(Username username) {
    Date date = timeServer.generate();
    Token token = new Token(tokenGenerator.code(username.getUsername()));
    return new UserSession(username, token, date);
  }
}
