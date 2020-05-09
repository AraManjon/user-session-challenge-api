package com.kubikdata.controllers;

import com.kubikdata.controllers.response.UserResponse;
import com.kubikdata.domain.Token;
import com.kubikdata.domain.UserSession;
import com.kubikdata.domain.Username;
import com.kubikdata.infrastructure.UserSessionInMemoryRepository;
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

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDataControllerShould {

  @Mock
  UserSessionInMemoryRepository userSessionInMemoryRepository;

  @InjectMocks
  private UserDataController userDataController;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void get_user_info_correctly() {

    String username = "username";
    String token = "thisIsAToken";
    Date date = new Date();
    UserResponse userResponseExpected = new UserResponse(username, token, date);

    when(userSessionInMemoryRepository.findUser(new Username(username), new Token(token))).thenReturn(userResponseExpected);
    ResponseEntity<UserResponse> response = userDataController.userInfoGet(username, token);

    Assert.assertNotNull(response);
    Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assert.assertEquals(userResponseExpected, response.getBody());
  }
}
