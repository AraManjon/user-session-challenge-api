package com.kubikdata.controllers;

import com.kubikdata.controllers.request.UserSessionRequest;
import com.kubikdata.controllers.response.SessionResponse;
import com.kubikdata.domain.entities.DTO;
import com.kubikdata.infrastructure.Repository;
import com.kubikdata.services.TimeServer;
import com.kubikdata.services.TokenGenerator;
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
  private Repository sessionInMemoryRepository;

  @InjectMocks
  private UserSessionController userSessionController;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void add_a_session_correctly(){

    String tokenExpected = "thisIsAToken";
    String username = "username";
    Date date = new Date();
    DTO.UserSession userSessionDTO = new DTO.UserSession();
    userSessionDTO.username = username;
    userSessionDTO.date = date;
    userSessionDTO.token = tokenExpected;
    UserSessionRequest userSessionRequest = new UserSessionRequest();
    userSessionRequest.setUsername(username);
    SessionResponse sessionResponseExpected = new SessionResponse();
    sessionResponseExpected.setToken(tokenExpected);
    when(tokenUsernameGenerator.code(username)).thenReturn(tokenExpected);
    when(timeDataServer.generate()).thenReturn(date);
    
    ResponseEntity<SessionResponse> response = userSessionController.addSession(userSessionRequest);

    verify(sessionInMemoryRepository).add(userSessionDTO);
    Assert.assertNotNull(response);
    Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assert.assertEquals(sessionResponseExpected, response.getBody());
  }

  @Test(expected = RuntimeException.class)
  public void throws_an_error_when_username_is_empty() {

    String username = "";
    UserSessionRequest userSessionRequest = new UserSessionRequest();
    userSessionRequest.setUsername(username);

    userSessionController.addSession(userSessionRequest);

  }
}
