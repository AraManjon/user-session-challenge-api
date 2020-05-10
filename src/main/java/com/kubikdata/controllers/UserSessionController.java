package com.kubikdata.controllers;

import com.kubikdata.controllers.request.UserSessionRequest;
import com.kubikdata.controllers.response.UserSessionResponse;
import com.kubikdata.domain.entities.Username;
import com.kubikdata.infrastructure.Repository;
import com.kubikdata.infrastructure.SessionInMemoryRepository;
import com.kubikdata.services.TimeServer;
import com.kubikdata.services.TokenGenerator;
import com.kubikdata.domain.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * this endpoint is needed to add a session id to a specific username
 *
 * @param userSessionRequest
 * @return userSessionResponse
 */

@RestController
public class UserSessionController {

  @Autowired
  TokenGenerator tokenUsernameGenerator;

  @Autowired
  TimeServer timeDataServer;

  @Autowired
  Repository sessionInMemoryRepository;

  @PostMapping(value = "/session")
  public ResponseEntity<UserSessionResponse> addSession(@RequestBody UserSessionRequest userSessionRequest) {

    UserSessionService userSessionService = new UserSessionService(tokenUsernameGenerator, timeDataServer, sessionInMemoryRepository);
    Username username = new Username(userSessionRequest.getUsername());

    return new ResponseEntity<>(userSessionService.addSession(username), HttpStatus.OK);
  }
}
