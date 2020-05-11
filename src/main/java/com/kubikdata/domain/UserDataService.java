package com.kubikdata.domain;

import com.kubikdata.controllers.response.UserResponse;
import com.kubikdata.domain.entities.DTO;
import com.kubikdata.domain.exceptions.SessionNotFound;
import com.kubikdata.domain.valueobjects.Token;
import com.kubikdata.domain.valueobjects.Username;
import com.kubikdata.infrastructure.Repository;

import java.util.Optional;

public class UserDataService {

  private final Repository sessionRepository;

  public UserDataService(Repository sessionRepository) {

    this.sessionRepository = sessionRepository;
  }

  public UserResponse findUser(Username username, Token token) {

    Optional<DTO.UserSession> userSessionDTO = sessionRepository.findUser(username, token);
    if(!userSessionDTO.isPresent()) throw new SessionNotFound("Session not found");
    return new UserResponse(userSessionDTO.get().username, userSessionDTO.get().token, userSessionDTO.get().date);
  }
}
