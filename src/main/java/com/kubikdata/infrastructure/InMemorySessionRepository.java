package com.kubikdata.infrastructure;

import com.kubikdata.domain.entities.DTO;
import com.kubikdata.domain.valueobjects.Token;
import com.kubikdata.domain.valueobjects.Username;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Component
public class InMemorySessionRepository implements Repository {

  private Collection<DTO.UserSession> userSessionList = new ArrayList<>();

  public Optional<DTO.UserSession> findUser(Username username, Token token) {

    return this.userSessionList.stream()
        .filter(userSession -> userSession.username.equals(username.getUsername()))
        .findFirst();
  }

  public void add(DTO.UserSession userSession) {
    this.userSessionList.add(userSession);
  }
}
