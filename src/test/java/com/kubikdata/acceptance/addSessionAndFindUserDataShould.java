package com.kubikdata.acceptance;

import com.kubikdata.controllers.response.UserResponse;
import com.kubikdata.domain.UserDataService;
import com.kubikdata.domain.UserSessionService;
import com.kubikdata.domain.valueobjects.Token;
import com.kubikdata.domain.valueobjects.Username;
import com.kubikdata.infrastructure.Repository;
import com.kubikdata.infrastructure.InMemorySessionRepository;
import com.kubikdata.services.TimeServer;
import com.kubikdata.services.TokenGenerator;
import com.kubikdata.utils.TokenTestFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class addSessionAndFindUserDataShould {

  @Mock
  TimeServer timeServer;

  @Mock
  TokenGenerator tokenGenerator;

  Repository sessionInMemoryRepository = new InMemorySessionRepository();

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void add_session_and_find_user_data(){

    String username = "username";
    String token = TokenTestFactory.createBy(username);
    Date date = new Date();
    UserResponse userResponseExpected = new UserResponse(username, token, date);
    UserSessionService userSessionService = new UserSessionService(tokenGenerator, timeServer, sessionInMemoryRepository);
    UserDataService userDataService = new UserDataService(sessionInMemoryRepository);
    when(tokenGenerator.code(username)).thenReturn(token);
    when(timeServer.generate()).thenReturn(date);

    userSessionService.addSession(new Username(username));

    Assert.assertEquals(userResponseExpected,
        userDataService.findUser(new Username(username), new Token(token)));
  }
}
