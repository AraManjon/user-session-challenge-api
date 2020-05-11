package com.kubikdata.unit;

import com.kubikdata.domain.dto.DTO;
import com.kubikdata.domain.valueobjects.Username;
import com.kubikdata.infrastructure.InMemoryUserSessionRepository;
import com.kubikdata.utils.TokenTestFactory;
import com.kubikdata.utils.UserSessionDTOTestFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class InMemorySessionShould {

  InMemoryUserSessionRepository inMemoryUserSessionRepository = new InMemoryUserSessionRepository();

  @Test
  public void add_and_find_userSession_correctly(){

    String username = "username";
    String token = TokenTestFactory.createBy(username);
    DTO.UserSession userSessionDTO =  UserSessionDTOTestFactory.create(username, token);
    Optional<DTO.UserSession> userSessionExpected = Optional.of(userSessionDTO);

    inMemoryUserSessionRepository.add(userSessionDTO);

    Assert.assertEquals(userSessionExpected,
        inMemoryUserSessionRepository.find(new Username(username)));
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

    inMemoryUserSessionRepository.add(userSession1);
    inMemoryUserSessionRepository.add(userSession2);
    inMemoryUserSessionRepository.add(userSession3);

    Assert.assertEquals(userSessionExpected,
        inMemoryUserSessionRepository.find(new Username(username)));
  }

  @Test
  public void update_userSession_when_username_exist_from_a_list_correctly(){

    String username = "username";
    String token = TokenTestFactory.createBy(username);
    DTO.UserSession userSession1 = UserSessionDTOTestFactory.create(username, token);
    String token2 = TokenTestFactory.createBy(username);
    DTO.UserSession userSession2 = UserSessionDTOTestFactory.create(username, token2);
    Optional<DTO.UserSession> userSessionExpected = Optional.of(userSession2);

    inMemoryUserSessionRepository.add(userSession1);
    inMemoryUserSessionRepository.add(userSession2);

    Assert.assertEquals(userSessionExpected,
        inMemoryUserSessionRepository.find(new Username(username)));
  }
}
