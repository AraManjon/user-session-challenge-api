package com.kubikdata.controllers;

import com.kubikdata.controllers.request.UserSessionRequest;
import com.kubikdata.controllers.response.UserResponse;
import com.kubikdata.domain.entities.DTO;
import com.kubikdata.infrastructure.Repository;
import com.kubikdata.infrastructure.InMemorySessionRepository;
import com.kubikdata.unit.TokenTestFactory;
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
  Repository sessionInMemoryRepository;

  @InjectMocks
  private UserDataController userDataController;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  public void createDummyRepository(DTO.UserSession userSessionDTO){
    sessionInMemoryRepository = new InMemorySessionRepository();
    sessionInMemoryRepository.add(userSessionDTO);
    userDataController.inMemorySessionRepository = sessionInMemoryRepository;
  }

  @Test
  public void get_user_info_correctly() {

    String username = "username";
    String token = TokenTestFactory.createBy(username);
    Date date = new Date();
    UserResponse userResponseExpected = new UserResponse(username, token, date);
    DTO.UserSession userSessionDTO = new DTO.UserSession();
    userSessionDTO.username = username;
    userSessionDTO.token = token;
    userSessionDTO.date = date;
    createDummyRepository(userSessionDTO);

    ResponseEntity<UserResponse> response = userDataController.userInfoGet(username, token);

    Assert.assertNotNull(response);
    Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assert.assertEquals(userResponseExpected, response.getBody());
  }

  @Test
  public void throws_an_error_when_token_is_empty() {

    String username = "username";
    String token = "";

    ResponseEntity<UserResponse> response = userDataController.userInfoGet(username, token);

    Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    Assert.assertEquals("Token can not be empty", response.getBody());
  }
}
