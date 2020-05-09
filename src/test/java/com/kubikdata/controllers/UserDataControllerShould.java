package com.kubikdata.controllers;

import com.kubikdata.controllers.response.UserResponse;
import com.kubikdata.domain.entities.DTO;
import com.kubikdata.infrastructure.Repository;
import com.kubikdata.infrastructure.SessionInMemoryRepository;
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
    sessionInMemoryRepository = new SessionInMemoryRepository();
    sessionInMemoryRepository.addUser(userSessionDTO);
    userDataController.sessionInMemoryRepository = sessionInMemoryRepository;
  }

  @Test
  public void get_user_info_correctly() {

    String username = "username";
    String token = "thisIsAToken";
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
}
