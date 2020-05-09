package com.kubikdata.controllers;

import com.kubikdata.controller.UserSessionController;
import com.kubikdata.controller.request.UserSessionRequest;
import com.kubikdata.controller.response.UserSessionResponse;
import com.kubikdata.services.TokenUsernameGenerator;
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

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserSessionControllerShould {

  @Mock
  TokenUsernameGenerator tokenUsernameGenerator;

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
    UserSessionRequest userSessionRequest = new UserSessionRequest();
    userSessionRequest.setUsername(username);
    when(tokenUsernameGenerator.code(username)).thenReturn("thisIsAToken");
    UserSessionResponse userSessionResponseExpected = new UserSessionResponse(tokenExpected);

    ResponseEntity<UserSessionResponse> response = userSessionController.addSession(userSessionRequest);

    Assert.assertNotNull(response);
    Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assert.assertEquals(userSessionResponseExpected, response.getBody());
  }
}
