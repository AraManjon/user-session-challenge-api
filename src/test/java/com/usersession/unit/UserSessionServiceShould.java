package com.usersession.unit;

import com.usersession.controllers.response.UserSessionResponse;
import com.usersession.domain.UserSessionService;
import com.usersession.domain.dto.DTO;
import com.usersession.domain.valueobjects.Username;
import com.usersession.domain.infrastructure.Repository;
import com.usersession.domain.infrastructure.TimeServer;
import com.usersession.services.TokenUsernameGenerator;
import com.usersession.utils.TokenTestFactory;
import com.usersession.utils.UserSessionDTOTestFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserSessionServiceShould {

  @Mock
  TokenUsernameGenerator tokenUsernameGenerator;

  @Mock
  TimeServer timeServer;

  @Mock
  Repository repository;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void create_a_token_correctly_when_add_user_session() {

    UserSessionService userSessionService = new UserSessionService(tokenUsernameGenerator, timeServer, repository);
    String username = "username";
    String token = TokenTestFactory.createBy(username);
    Date date = new Date();
    DTO.UserSession userSessionDTO = new DTO.UserSession();
    userSessionDTO.username = username;
    userSessionDTO.token = token;
    userSessionDTO.date = date;
    UserSessionResponse userSessionResponse = new UserSessionResponse();
    userSessionResponse.setToken(token);
    when(tokenUsernameGenerator.code(username)).thenReturn(token);
    when(timeServer.generate()).thenReturn(date);

    Assert.assertEquals(userSessionResponse, userSessionService.addSession(new Username(username)));
    verify(repository).add(userSessionDTO);
  }

  @Test
  public void update_user_session_when_exist() {

    UserSessionService userSessionService = new UserSessionService(tokenUsernameGenerator, timeServer, repository);
    String username = "username";
    String token = TokenTestFactory.createBy(username);
    Optional<DTO.UserSession> userSessionDTO = Optional.of(UserSessionDTOTestFactory.create(username, token));
    Date date = new Date();
    DTO.UserSession userSessionUpdatedDTO = new DTO.UserSession();
    userSessionUpdatedDTO.username = username;
    userSessionUpdatedDTO.token = token;
    userSessionUpdatedDTO.date = new Date();
    UserSessionResponse userSessionResponse = new UserSessionResponse();
    userSessionResponse.setToken(token);
    when(repository.find(new Username(username))).thenReturn(userSessionDTO);
    when(tokenUsernameGenerator.code(username)).thenReturn(token);
    when(timeServer.generate()).thenReturn(date);

    Assert.assertEquals(userSessionResponse, userSessionService.addSession(new Username(username)));
    verify(repository).add(userSessionUpdatedDTO);
  }
}
