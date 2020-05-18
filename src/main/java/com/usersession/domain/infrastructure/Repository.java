package com.usersession.domain.infrastructure;

import com.usersession.domain.dto.DTO;
import com.usersession.domain.valueobjects.Username;

import java.util.Optional;

public interface Repository {

  Optional<DTO.UserSession> find(Username username);

  void add(DTO.UserSession userSession);

  void remove(Username username);
}
