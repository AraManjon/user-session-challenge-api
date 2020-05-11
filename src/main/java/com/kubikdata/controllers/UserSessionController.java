package com.kubikdata.controllers;

import com.kubikdata.controllers.request.UserSessionRequest;
import com.kubikdata.domain.exceptions.NotAllowedEmptyUsername;
import com.kubikdata.domain.valueobjects.Username;
import com.kubikdata.domain.infrastructure.Repository;
import com.kubikdata.domain.infrastructure.TimeServer;
import com.kubikdata.domain.infrastructure.TokenGenerator;
import com.kubikdata.domain.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * this endpoint is needed to add a session id to a specific username
 */

@RestController
public class UserSessionController {

  @Autowired
  TokenGenerator tokenUsernameGenerator;

  @Autowired
  TimeServer dateServer;

  @Autowired
  Repository inMemorySessionRepository;

  /**
   * @param userSessionRequest username to create userSession
   * @return UserSessionResponse
   * @throws NotAllowedEmptyUsername "Username cannot be empty"
   * @throws RuntimeException        "Service unavailable"
   */
  @PostMapping(value = "/session")
  public ResponseEntity<Object> addSession(@RequestBody UserSessionRequest userSessionRequest) {

    try {

      UserSessionService userSessionService = new UserSessionService(tokenUsernameGenerator, dateServer, inMemorySessionRepository);
      Username username = new Username(userSessionRequest.getUsername());

      return new ResponseEntity<>(userSessionService.addSession(username), HttpStatus.OK);
    } catch (NotAllowedEmptyUsername exception) {

      return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception exception) {

      return new ResponseEntity<>("Service unavailable", HttpStatus.SERVICE_UNAVAILABLE);
    }
  }
}
