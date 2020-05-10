package com.kubikdata.domain;

import com.kubikdata.controllers.response.UserResponse;
import com.kubikdata.domain.entities.DTO;
import com.kubikdata.domain.entities.Token;
import com.kubikdata.domain.entities.Username;
import com.kubikdata.infrastructure.Repository;

public class UserDataService {

  private final Repository sessionRepository;

  public UserDataService(Repository sessionRepository) {

    this.sessionRepository = sessionRepository;
  }

  public UserResponse findUser(Username username, Token token) {
    DTO.UserSession userSessionDTO = sessionRepository.findUser(username, token);
    return new UserResponse(userSessionDTO.username, userSessionDTO.token, userSessionDTO.date);
  }
}
