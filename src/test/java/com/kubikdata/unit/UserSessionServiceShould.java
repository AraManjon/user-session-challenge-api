package com.kubikdata.unit;

import com.kubikdata.controllers.response.UserSessionResponse;
import com.kubikdata.domain.UserSessionService;
import com.kubikdata.domain.entities.Username;
import com.kubikdata.infrastructure.Repository;
import com.kubikdata.services.TimeServer;
import com.kubikdata.services.TokenUsernameGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

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
    String token = "randomUserToken";
    String username = "username";
    UserSessionResponse userSessionResponse = new UserSessionResponse();
    userSessionResponse.setToken(token);
    when(tokenUsernameGenerator.code(username)).thenReturn(token);

    Assert.assertEquals(userSessionResponse, userSessionService.addSession(new Username(username)));
  }
}
