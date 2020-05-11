package com.kubikdata.infrastructure;

import com.kubikdata.domain.dto.DTO;
import com.kubikdata.domain.infrastructure.Repository;
import com.kubikdata.domain.valueobjects.Token;
import com.kubikdata.domain.valueobjects.Username;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@Component
public class InMemorySessionRepository implements Repository {

  private final List<DTO.UserSession> userSessionList = new ArrayList<>();

  public Optional<DTO.UserSession> find(Username username, Token token) {

    Predicate<DTO.UserSession> isSameUsername = userSession ->
        userSession.username.equals(username.getUsername());
    Predicate<DTO.UserSession> isSameToken = userSession ->
        userSession.token.equals(token.getToken());

    return this.userSessionList.stream()
        .filter(isSameUsername.and(isSameToken))
        .findFirst();
  }

  public void add(DTO.UserSession userSessionDTO) {

    Predicate<DTO.UserSession> isSameUsername = userSession ->
        userSession.username.equals(userSessionDTO.username);

    this.userSessionList.removeIf(isSameUsername);
    this.userSessionList.add(userSessionDTO);
  }
}
