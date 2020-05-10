package com.kubikdata.infrastructure;

import com.kubikdata.domain.entities.DTO;
import com.kubikdata.domain.entities.Token;
import com.kubikdata.domain.entities.Username;
import org.springframework.stereotype.Component;

@Component
public class InMemorySessionRepository implements Repository {

  private DTO.UserSession userSessionList;

  public DTO.UserSession findUser(Username username, Token token) {
    return userSessionList;
  }

  public void add(DTO.UserSession userSession) {
    this.userSessionList = userSession;
  }
}
