package com.usersession.infrastructure;

import com.usersession.domain.dto.DTO;
import com.usersession.domain.valueobjects.Username;
import com.usersession.utils.TokenTestFactory;
import com.usersession.utils.UserSessionDTOTestFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class InMemorySessionShould {

  InMemorySessionRepository inMemorySessionRepository = new InMemorySessionRepository();

  @Test
  public void add_and_find_userSession_correctly(){

    String username = "username";
    String token = TokenTestFactory.createBy(username);
    DTO.UserSession userSessionDTO =  UserSessionDTOTestFactory.create(username, token);
    Optional<DTO.UserSession> userSessionExpected = Optional.of(userSessionDTO);

    inMemorySessionRepository.add(userSessionDTO);

    Assert.assertEquals(userSessionExpected,
        inMemorySessionRepository.find(new Username(username)));
  }

  @Test
  public void add_and_find_userSession_from_a_list_correctly(){

    String username = "username";
    String token = TokenTestFactory.createBy(username);
    DTO.UserSession userSession1 = UserSessionDTOTestFactory.create(username, token);
    String username2 = "username2";
    String token2 = TokenTestFactory.createBy(username2);
    DTO.UserSession userSession2 = UserSessionDTOTestFactory.create(username2, token2);
    String username3 = "username3";
    String token3 = TokenTestFactory.createBy(username3);
    DTO.UserSession userSession3 = UserSessionDTOTestFactory.create(username3, token3);
    Optional<DTO.UserSession> userSessionExpected = Optional.of(userSession1);

    inMemorySessionRepository.add(userSession1);
    inMemorySessionRepository.add(userSession2);
    inMemorySessionRepository.add(userSession3);

    Assert.assertEquals(userSessionExpected,
        inMemorySessionRepository.find(new Username(username)));
  }

  @Test
  public void remove_userSession_from_a_list_correctly(){

    String username = "username";
    String token = TokenTestFactory.createBy(username);
    DTO.UserSession userSession = UserSessionDTOTestFactory.create(username, token);

    inMemorySessionRepository.add(userSession);
    inMemorySessionRepository.remove(new Username(username));

    Assert.assertEquals(Optional.empty(), inMemorySessionRepository.find(new Username(username)));

  }
}
