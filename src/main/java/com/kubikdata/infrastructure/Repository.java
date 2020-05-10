package com.kubikdata.infrastructure;

import com.kubikdata.domain.entities.DTO;
import com.kubikdata.domain.valueobjects.Token;
import com.kubikdata.domain.valueobjects.Username;

public interface Repository {
  DTO.UserSession findUser(Username username, Token token);
  void add(DTO.UserSession userSession);
}
