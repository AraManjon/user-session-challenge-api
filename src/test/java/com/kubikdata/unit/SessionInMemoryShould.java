package com.kubikdata.unit;

import com.kubikdata.domain.entities.DTO;
import com.kubikdata.domain.valueobjects.Token;
import com.kubikdata.domain.valueobjects.Username;
import com.kubikdata.infrastructure.InMemorySessionRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class SessionInMemoryShould {

  InMemorySessionRepository inMemorySessionRepository = new InMemorySessionRepository();

  @Test
  public void add_and_find_userSession_correctly(){

    String username = "username";
    String token = "token";
    DTO.UserSession userSessionExpected = new DTO.UserSession();
    userSessionExpected.username = username;
    userSessionExpected.token = token;
    userSessionExpected.date = new Date();

    inMemorySessionRepository.add(userSessionExpected);

    Assert.assertEquals(userSessionExpected,
        inMemorySessionRepository.findUser(new Username(username), new Token(token)));
  }
}
