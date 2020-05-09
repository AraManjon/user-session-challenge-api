package com.kubikdata.infrastructure;

import com.kubikdata.controllers.response.UserResponse;
import com.kubikdata.domain.Token;
import com.kubikdata.domain.Username;

public class UserSessionInMemoryRepository implements Repository{
  public UserResponse findUser(Username username, Token token) {
    return null;
  }
}
