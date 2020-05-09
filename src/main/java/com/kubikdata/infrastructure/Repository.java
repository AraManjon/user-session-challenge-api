package com.kubikdata.infrastructure;

import com.kubikdata.domain.entities.DTO;
import com.kubikdata.domain.entities.Token;
import com.kubikdata.domain.entities.Username;

public interface Repository {
  DTO.UserSession findUser(Username username, Token token);
  void addUser(DTO.UserSession userSession);
}
