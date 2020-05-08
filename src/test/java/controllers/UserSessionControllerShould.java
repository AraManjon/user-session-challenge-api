package controllers;

import com.kubikdata.controller.UserSessionController;
import com.kubikdata.controller.request.UserSessionRequest;
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
  public void create_a_token_by_username_correctly(){

    String tokenExpected = "thisIsAToken";
    String username = "username";
    UserSessionRequest userSessionRequest = new UserSessionRequest();
    userSessionRequest.setUsername(username);
    when(tokenUsernameGenerator.generate(username)).thenReturn("thisIsAToken");

    ResponseEntity<String> userSessionResponse = userSessionController.addSession(userSessionRequest);

    Assert.assertNotNull(userSessionResponse);
    Assert.assertEquals(HttpStatus.OK, userSessionResponse.getStatusCode());
    Assert.assertEquals(tokenExpected, userSessionResponse.getBody());
  }
}
