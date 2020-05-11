package com.kubikdata.domain.infrastructure;

import com.kubikdata.domain.dto.DTO;
import com.kubikdata.domain.valueobjects.Token;
import com.kubikdata.domain.valueobjects.Username;

import java.util.Optional;

public interface Repository {
  Optional<DTO.UserSession> find(Username username, Token token);
  void add(DTO.UserSession userSession);
}
