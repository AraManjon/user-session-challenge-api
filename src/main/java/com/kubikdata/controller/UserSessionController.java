package com.kubikdata.controller;

import com.kubikdata.controller.request.UserSessionRequest;
import com.kubikdata.controller.response.UserSessionResponse;
import com.kubikdata.domain.Username;
import com.kubikdata.services.TokenGenerator;
import com.kubikdata.services.TokenUsernameGenerator;
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
 * @return token
 */

@RestController
public class UserSessionController {

  @Autowired
  TokenGenerator tokenUsernameGenerator = new TokenUsernameGenerator();

  @PostMapping(value = "/session")
  public ResponseEntity<UserSessionResponse> addSession(@RequestBody UserSessionRequest userSessionRequest) {

    UserSessionService userSessionService = new UserSessionService(tokenUsernameGenerator);

    return new ResponseEntity<>(userSessionService.addSession(new Username(userSessionRequest.getUsername())), HttpStatus.OK);
  }
}
