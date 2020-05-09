package com.kubikdata.domain;

import com.kubikdata.controller.response.UserSessionResponse;
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

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void create_a_token_correctly_when_add_user_session() {

    UserSessionService userSessionService = new UserSessionService(tokenUsernameGenerator);
    String token = "randomUserToken";
    String username = "username";
    when(tokenUsernameGenerator.code(username)).thenReturn(token);

    Assert.assertEquals(new UserSessionResponse(token), userSessionService.addSession(new Username(username)));
  }
}
