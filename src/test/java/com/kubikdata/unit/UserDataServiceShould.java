package com.kubikdata.unit;

import com.kubikdata.controllers.response.UserResponse;
import com.kubikdata.domain.UserDataService;
import com.kubikdata.domain.entities.DTO;
import com.kubikdata.domain.entities.Token;
import com.kubikdata.domain.entities.Username;
import com.kubikdata.infrastructure.Repository;
import com.kubikdata.infrastructure.InMemorySessionRepository;
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
  Repository sessionInMemoryRepository;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  public void createDummyRepository(DTO.UserSession userSessionDTO){
    sessionInMemoryRepository = new InMemorySessionRepository();
    sessionInMemoryRepository.add(userSessionDTO);
  }

  @Test
  public void find_user_data_correctly() {

    String username = "username";
    String token = "thisIsAToken";
    Date date = new Date();
    UserResponse userResponseExpected = new UserResponse(username, token, date);
    DTO.UserSession userSessionDTO = new DTO.UserSession();
    userSessionDTO.username = username;
    userSessionDTO.token = token;
    userSessionDTO.date = date;
    createDummyRepository(userSessionDTO);
    UserDataService userDataService = new UserDataService(sessionInMemoryRepository);

    Assert.assertEquals(userResponseExpected, userDataService.findUser(new Username(username), new Token(token)));
  }
}
