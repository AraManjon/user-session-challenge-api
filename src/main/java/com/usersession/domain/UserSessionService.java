package com.usersession.domain;

import com.usersession.controllers.response.UserSessionResponse;
import com.usersession.domain.dto.DTO;
import com.usersession.domain.valueobjects.Token;
import com.usersession.domain.entities.UserSession;
import com.usersession.domain.valueobjects.Username;
import com.usersession.domain.infrastructure.Repository;
import com.usersession.domain.infrastructure.TimeServer;
import com.usersession.domain.infrastructure.TokenGenerator;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

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

    removeIfUserSessionExist(username);

    UserSession userSession = createUserSession(username);

    DTO.UserSession userSessionDTO = userSession.createDTO();
    repository.add(userSessionDTO);

    return createUserSessionResponse(userSessionDTO);
  }

  private void removeIfUserSessionExist(Username username) {
    Optional<DTO.UserSession> userSessionFound = repository.find(username);
    if(userSessionFound.isPresent()) repository.remove(username);
  }

  private UserSession createUserSession(Username username) {
    Date date = timeServer.generate();
    Token token = new Token(tokenGenerator.code(username.getUsername()));
    return new UserSession(username, token, date);
  }

  private UserSessionResponse createUserSessionResponse(DTO.UserSession userSessionDTO) {
    UserSessionResponse userSessionResponse = new UserSessionResponse();
    userSessionResponse.setToken(userSessionDTO.token);
    return userSessionResponse;
  }
}
