package com.kubikdata.domain;

import com.kubikdata.controllers.response.UserDataResponse;
import com.kubikdata.domain.dto.DTO;
import com.kubikdata.domain.exceptions.UserSessionNotFound;
import com.kubikdata.domain.valueobjects.Token;
import com.kubikdata.domain.valueobjects.Username;
import com.kubikdata.domain.infrastructure.Repository;
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

    Optional<DTO.UserSession> userSessionDTO = repository.find(username, token);

    if (!userSessionDTO.isPresent()) throw new UserSessionNotFound("Session not found");

    return new UserDataResponse(userSessionDTO.get().username, userSessionDTO.get().token, userSessionDTO.get().date);
  }
}
