package com.usersession.unit;

import com.usersession.controllers.response.UserDataResponse;
import com.usersession.domain.UserDataService;
import com.usersession.domain.dto.DTO;
import com.usersession.domain.exceptions.UserSessionNotFound;
import com.usersession.domain.valueobjects.Token;
import com.usersession.domain.valueobjects.Username;
import com.usersession.domain.infrastructure.Repository;
import com.usersession.infrastructure.InMemorySessionRepository;
import com.usersession.utils.TokenTestFactory;
import com.usersession.utils.UserSessionDTOTestFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class UserDataServiceShould {

  @Mock
  Repository repository;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  public void createDummyRepository(DTO.UserSession userSessionDTO){
    repository = new InMemorySessionRepository();
    repository.add(userSessionDTO);
  }

  @Test
  public void find_user_session_correctly() {

    String username = "username";
    String token = TokenTestFactory.createBy(username);
    Date date = new Date();
    UserDataResponse userDataResponseExpected = new UserDataResponse(username, token, date);
    DTO.UserSession userSessionDTO = new DTO.UserSession();
    userSessionDTO.username = username;
    userSessionDTO.token = token;
    userSessionDTO.date = date;
    createDummyRepository(userSessionDTO);
    UserDataService userDataService = new UserDataService(repository);

    Assert.assertEquals(userDataResponseExpected, userDataService.findUser(new Username(username), new Token(token)));
  }

  @Test(expected = UserSessionNotFound.class)
  public void throw_an_error_if_user_session_is_not_found_by_username(){

    String username = "notFoundUsername";
    String token = TokenTestFactory.createBy(username);

    UserDataService userDataService = new UserDataService(repository);

    userDataService.findUser(new Username(username), new Token(token));
  }

  @Test(expected = UserSessionNotFound.class)
  public void throw_an_error_if_user_session_is_not_found_by_token(){

    String username = "username";
    String token = TokenTestFactory.createBy(username);
    String tokenNotFound = TokenTestFactory.createBy("otherUserName");
    DTO.UserSession userSessionDTO = UserSessionDTOTestFactory.create(username, token);
    createDummyRepository(userSessionDTO);

    UserDataService userDataService = new UserDataService(repository);

    userDataService.findUser(new Username(username), new Token(tokenNotFound));
  }
}
