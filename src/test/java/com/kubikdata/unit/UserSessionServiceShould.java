package com.kubikdata.unit;

import com.kubikdata.controllers.response.UserSessionResponse;
import com.kubikdata.domain.UserSessionService;
import com.kubikdata.domain.dto.DTO;
import com.kubikdata.domain.valueobjects.Username;
import com.kubikdata.domain.infrastructure.Repository;
import com.kubikdata.domain.infrastructure.TimeServer;
import com.kubikdata.services.TokenUsernameGenerator;
import com.kubikdata.utils.TokenTestFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserSessionServiceShould {

  @Mock
  TokenUsernameGenerator tokenUsernameGenerator;

  @Mock
  TimeServer timeServer;

  @Mock
  Repository sessionInMemoryRepository;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void create_a_token_correctly_when_add_user_session() {

    UserSessionService userSessionService = new UserSessionService(tokenUsernameGenerator, timeServer, sessionInMemoryRepository);
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
    verify(sessionInMemoryRepository).add(userSessionDTO);
  }
}
