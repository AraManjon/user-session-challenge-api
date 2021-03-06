package com.usersession.controllers;

import com.usersession.controllers.response.UserDataResponse;
import com.usersession.domain.dto.DTO;
import com.usersession.domain.valueobjects.Username;
import com.usersession.domain.infrastructure.Repository;
import com.usersession.infrastructure.InMemorySessionRepository;
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
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDataControllerShould {

  @Mock
  Repository repository;

  @InjectMocks
  private UserDataController userDataController;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  public void createDummyRepository(DTO.UserSession userSessionDTO) {
    repository = new InMemorySessionRepository();
    repository.add(userSessionDTO);
    userDataController.repository = repository;
  }

  @Test
  public void get_user_info_correctly() {

    String username = "username";
    String token = TokenTestFactory.createBy(username);
    Date date = new Date();
    UserDataResponse userDataResponseExpected = new UserDataResponse(username, token, date);
    DTO.UserSession userSessionDTO = new DTO.UserSession();
    userSessionDTO.username = username;
    userSessionDTO.token = token;
    userSessionDTO.date = date;
    createDummyRepository(userSessionDTO);

    ResponseEntity<Object> response = userDataController.userInfoGet(username, token);

    Assert.assertNotNull(response);
    Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assert.assertEquals(userDataResponseExpected, response.getBody());
  }

  @Test
  public void throws_an_error_when_token_is_not_valid() {

    String username = "username";
    String token = "token";

    ResponseEntity<Object> response = userDataController.userInfoGet(username, token);

    Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    Assert.assertEquals("Token not valid", response.getBody());
  }

  @Test
  public void throw_an_error_when_not_found_a_user_session() {

    String username = "username";
    String token = TokenTestFactory.createBy(username);
    when(repository.find(new Username(username))).thenReturn(Optional.empty());
    ResponseEntity<Object> response = userDataController.userInfoGet(username, token);

    Assert.assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
    Assert.assertEquals("Session not found", response.getBody());
  }

  @Test
  public void throws_an_error_when_username_is_not_valid() {

    String username = "_+@";
    String token = TokenTestFactory.createBy(username);

    ResponseEntity<Object> response = userDataController.userInfoGet(username, token);

    Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    Assert.assertEquals("Username not valid", response.getBody());
  }
}
