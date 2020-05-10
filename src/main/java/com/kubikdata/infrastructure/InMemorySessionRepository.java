package com.kubikdata.infrastructure;

import com.kubikdata.domain.entities.DTO;
import com.kubikdata.domain.valueobjects.Token;
import com.kubikdata.domain.valueobjects.Username;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class InMemorySessionRepository implements Repository {

  private final Collection<DTO.UserSession> userSessionList = new ArrayList<>();

  public Optional<DTO.UserSession> findUser(Username username, Token token) {

    Predicate<DTO.UserSession> isSameUsername = userSession -> userSession.username.equals(username.getUsername());
    Predicate<DTO.UserSession> isSameToken = userSession -> userSession.token.equals(token.getToken());

    return this.userSessionList.stream()
        .filter(isSameUsername.and(isSameToken))
        .findFirst();
  }

  public void add(DTO.UserSession userSession) {
    this.userSessionList.add(userSession);
  }
}
