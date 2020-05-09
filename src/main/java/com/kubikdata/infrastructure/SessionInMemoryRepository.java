package com.kubikdata.infrastructure;

import com.kubikdata.domain.entities.DTO;
import com.kubikdata.domain.entities.Token;
import com.kubikdata.domain.entities.Username;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SessionInMemoryRepository implements Repository {

  private final List<DTO.UserSession> userSessionList = new ArrayList<>();

  public DTO.UserSession findUser(Username username, Token token) {
    return userSessionList.get(0);
  }

  public void addUser(DTO.UserSession userSession) {
    this.userSessionList.add(userSession);
  }
}
