package com.kubikdata.infrastructure;

import com.kubikdata.domain.DTO;
import com.kubikdata.domain.Token;
import com.kubikdata.domain.Username;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SessionInMemoryRepository implements Repository {

  private List<DTO.UserSession> userSessionList = new ArrayList<>();

  public DTO.UserSession findUser(Username username, Token token) {
    return userSessionList.get(0);
  }

  public void addUser(DTO.UserSession userSession) {
    this.userSessionList.add(userSession);
  }
}
