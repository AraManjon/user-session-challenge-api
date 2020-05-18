package com.usersession.controllers;

import com.usersession.controllers.request.UserSessionRequest;
import com.usersession.controllers.response.UserSessionResponse;
import com.usersession.domain.dto.DTO;
import com.usersession.domain.infrastructure.Repository;
import com.usersession.domain.infrastructure.TimeServer;
import com.usersession.domain.infrastructure.TokenGenerator;
import com.usersession.utils.TokenTestFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserSessionControllerShould {

  @Mock
  private TokenGenerator tokenUsernameGenerator;

  @Mock
  private TimeServer timeDataServer;

  @Mock
  private Repository repository;

  @InjectMocks
  private UserSessionController userSessionController;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void add_a_session_correctly() {

    String username = "username";
    String tokenExpected = TokenTestFactory.createBy(username);
    Date date = new Date();
    DTO.UserSession userSessionDTO = new DTO.UserSession();
    userSessionDTO.username = username;
    userSessionDTO.date = date;
    userSessionDTO.token = tokenExpected;
    UserSessionRequest userSessionRequest = new UserSessionRequest();
    userSessionRequest.setUsername(username);
    UserSessionResponse userSessionResponseExpected = new UserSessionResponse();
    userSessionResponseExpected.setToken(tokenExpected);
    when(tokenUsernameGenerator.code(username)).thenReturn(tokenExpected);
    when(timeDataServer.generate()).thenReturn(date);

    ResponseEntity<Object> response = userSessionController.addSession(userSessionRequest);

    verify(repository).add(userSessionDTO);
    Assert.assertNotNull(response);
    Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assert.assertEquals(userSessionResponseExpected, response.getBody());
  }

  @Test
  public void throws_an_error_when_username_is_not_valid() {

    String username = "_+@";
    UserSessionRequest userSessionRequest = new UserSessionRequest();
    userSessionRequest.setUsername(username);

    ResponseEntity<Object> response = userSessionController.addSession(userSessionRequest);

    Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    Assert.assertEquals("Username not valid", response.getBody());
  }
}
