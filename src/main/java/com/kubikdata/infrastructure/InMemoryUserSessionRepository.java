package com.kubikdata.infrastructure;

import com.kubikdata.domain.dto.DTO;
import com.kubikdata.domain.infrastructure.Repository;
import com.kubikdata.domain.valueobjects.Username;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@Component
public class InMemoryUserSessionRepository implements Repository {

  private final List<DTO.UserSession> userSessionList = new ArrayList<>();

  public Optional<DTO.UserSession> find(Username username) {

    Predicate<DTO.UserSession> isSameUsername = userSession ->
        userSession.username.equals(username.getUsername());

    return this.userSessionList.stream()
        .filter(isSameUsername)
        .findFirst();
  }

  public void add(DTO.UserSession userSessionDTO) {

    this.userSessionList.add(userSessionDTO);
  }

  @Override
  public void remove(Username username) {

    this.userSessionList.removeIf(userSession ->
        userSession.username.equals(username.getUsername()));
  }
}
