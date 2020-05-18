package com.usersession.acceptance;

import com.usersession.controllers.response.UserDataResponse;
import com.usersession.domain.UserDataService;
import com.usersession.domain.UserSessionService;
import com.usersession.domain.valueobjects.Token;
import com.usersession.domain.valueobjects.Username;
import com.usersession.domain.infrastructure.Repository;
import com.usersession.infrastructure.InMemorySessionRepository;
import com.usersession.domain.infrastructure.TimeServer;
import com.usersession.domain.infrastructure.TokenGenerator;
import com.usersession.utils.TokenTestFactory;
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
public class addUserSessionAndFindUserDataShould {

  @Mock
  TimeServer timeServer;

  @Mock
  TokenGenerator tokenGenerator;

  Repository repository = new InMemorySessionRepository();

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void add_user_session_and_find_user_data(){

    String username = "username";
    String token = TokenTestFactory.createBy(username);
    Date date = new Date();
    UserDataResponse userDataResponseExpected = new UserDataResponse(username, token, date);
    UserSessionService userSessionService = new UserSessionService(tokenGenerator, timeServer, repository);
    UserDataService userDataService = new UserDataService(repository);
    when(tokenGenerator.code(username)).thenReturn(token);
    when(timeServer.generate()).thenReturn(date);

    userSessionService.addSession(new Username(username));

    Assert.assertEquals(userDataResponseExpected,
        userDataService.findUser(new Username(username), new Token(token)));
  }
}
