package com.kubikdata.unit;

import com.kubikdata.domain.entities.DTO;
import com.kubikdata.domain.valueobjects.Token;
import com.kubikdata.domain.valueobjects.Username;
import com.kubikdata.infrastructure.InMemorySessionRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class InMemorySessionShould {

  InMemorySessionRepository inMemorySessionRepository = new InMemorySessionRepository();

  @Test
  public void add_and_find_userSession_correctly(){

    String username = "username";
    String token = TokenTestFactory.createBy(username);
    DTO.UserSession userSessionExpected = new DTO.UserSession();
    userSessionExpected.username = username;
    userSessionExpected.token = token;
    userSessionExpected.date = new Date();

    inMemorySessionRepository.add(userSessionExpected);

    Assert.assertEquals(userSessionExpected,
        inMemorySessionRepository.findUser(new Username(username), new Token(token)));
  }

  @Test
  public void add_and_find_userSession_from_a_list_correctly(){

    String username = "username";
    String token = TokenTestFactory.createBy(username);
    DTO.UserSession userSessionExpected = UserSessionDTOTestFactory.create(username, token);
    String username2 = "username";
    String token2 = TokenTestFactory.createBy(username2);
    DTO.UserSession userSession2 = UserSessionDTOTestFactory.create(username2, token2);
    String username3 = "username";
    String token3 = TokenTestFactory.createBy(username3);
    DTO.UserSession userSession3 = UserSessionDTOTestFactory.create(username3, token3);

    inMemorySessionRepository.add(userSessionExpected);
    inMemorySessionRepository.add(userSession2);
    inMemorySessionRepository.add(userSession3);


    Assert.assertEquals(userSessionExpected,
        inMemorySessionRepository.findUser(new Username(username), new Token(token)));
  }
}
