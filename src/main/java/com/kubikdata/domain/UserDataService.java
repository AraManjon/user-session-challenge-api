package com.kubikdata.domain;

import com.kubikdata.controllers.response.UserResponse;
import com.kubikdata.domain.entities.DTO;
import com.kubikdata.domain.entities.Token;
import com.kubikdata.domain.entities.Username;
import com.kubikdata.infrastructure.Repository;

public class UserDataService {

  private final Repository repository;

  public UserDataService(Repository repository) {

    this.repository = repository;
  }

  public UserResponse findUser(Username username, Token token) {
    DTO.UserSession userSessionDTO = repository.findUser(username, token);
    return new UserResponse(userSessionDTO.username, userSessionDTO.token, userSessionDTO.date);
  }
}
