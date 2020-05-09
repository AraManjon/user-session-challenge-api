package com.kubikdata.infrastructure;

import com.kubikdata.domain.DTO;
import com.kubikdata.domain.Token;
import com.kubikdata.domain.Username;

public interface Repository {
  DTO.UserSession findUser(Username username, Token token);
  void addUser(DTO.UserSession userSession);
}
