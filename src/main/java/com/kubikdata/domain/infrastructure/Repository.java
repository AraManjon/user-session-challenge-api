package com.kubikdata.domain.infrastructure;

import com.kubikdata.domain.dto.DTO;
import com.kubikdata.domain.valueobjects.Username;

import java.util.Optional;

public interface Repository {

  Optional<DTO.UserSession> find(Username username);

  void add(DTO.UserSession userSession);

  void remove(Username username);
}
