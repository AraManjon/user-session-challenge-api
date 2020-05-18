package com.usersession.controllers;

import com.usersession.controllers.request.UserSessionRequest;
import com.usersession.domain.exceptions.UsernameIsNotValid;
import com.usersession.domain.valueobjects.Username;
import com.usersession.domain.infrastructure.Repository;
import com.usersession.domain.infrastructure.TimeServer;
import com.usersession.domain.infrastructure.TokenGenerator;
import com.usersession.domain.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * this endpoint is needed to add a session id to a specific username
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserSessionController {

  @Autowired
  TokenGenerator tokenUsernameGenerator;

  @Autowired
  TimeServer dateServer;

  @Autowired
  Repository repository;

  /**
   * @param userSessionRequest username to create userSession
   * @return UserSessionResponse
   * @throws UsernameIsNotValid "Username cannot be empty"
   * @throws RuntimeException        "Service unavailable"
   */
  @PostMapping(value = "/session")
  public ResponseEntity<Object> addSession(@RequestBody UserSessionRequest userSessionRequest) {

    try {

      UserSessionService userSessionService = new UserSessionService(tokenUsernameGenerator, dateServer, repository);
      Username username = new Username(userSessionRequest.getUsername());

      return new ResponseEntity<>(userSessionService.addSession(username), HttpStatus.OK);
    } catch (UsernameIsNotValid exception) {

      return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (RuntimeException exception) {

      return new ResponseEntity<>("Service unavailable", HttpStatus.SERVICE_UNAVAILABLE);
    }
  }
}
