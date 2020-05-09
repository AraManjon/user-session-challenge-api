package com.kubikdata.unit;

import com.kubikdata.domain.entities.DTO;
import com.kubikdata.domain.entities.Token;
import com.kubikdata.domain.entities.Username;
import com.kubikdata.infrastructure.SessionInMemoryRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class SessionInMemoryShould {

  SessionInMemoryRepository sessionInMemoryRepository = new SessionInMemoryRepository();

  @Test
  public void add_and_find_userSession_correctly(){

    String username = "username";
    String token = "token";
    DTO.UserSession userSessionExpected = new DTO.UserSession();
    userSessionExpected.username = username;
    userSessionExpected.token = token;
    userSessionExpected.date = new Date();

    sessionInMemoryRepository.addUser(userSessionExpected);

    Assert.assertEquals(userSessionExpected,
        sessionInMemoryRepository.findUser(new Username(username), new Token(token)));
  }
}
