package com.usersession.domain;

import com.usersession.controllers.response.UserDataResponse;
import com.usersession.domain.dto.DTO;
import com.usersession.domain.exceptions.UserSessionNotFound;
import com.usersession.domain.valueobjects.Token;
import com.usersession.domain.valueobjects.Username;
import com.usersession.domain.infrastructure.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Find userSession from repository by username and token
 */
@Service
public class UserDataService {

  private final Repository repository;

  public UserDataService(Repository repository) {

    this.repository = repository;
  }

  public UserDataResponse findUser(Username username, Token token) {

    Optional<DTO.UserSession> userSessionDTO = repository.find(username);

    if (!userSessionDTO.isPresent() || !userSessionDTO.get().token.equals(token.getToken())) throw new UserSessionNotFound("Session not found");

    return new UserDataResponse(userSessionDTO.get().username, userSessionDTO.get().token, userSessionDTO.get().date);
  }
}
