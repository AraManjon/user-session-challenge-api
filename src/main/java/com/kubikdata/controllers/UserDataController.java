package com.kubikdata.controllers;

import com.kubikdata.controllers.response.UserResponse;
import com.kubikdata.domain.entities.Token;
import com.kubikdata.domain.UserDataService;
import com.kubikdata.domain.entities.Username;
import com.kubikdata.infrastructure.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * this class is used to return data info based on its session token,
 * choose one of the endpoints to return data info
 * @param username
 * @param token
 * @return userResponse
 */

@RestController
public class UserDataController {

  @Autowired
  Repository inMemorySessionRepository;

  @GetMapping(value = "/info/{username}/{token}")
  public ResponseEntity<UserResponse> userInfoGet(@PathVariable String username, @PathVariable String token) {

    UserDataService userDataService = new UserDataService(inMemorySessionRepository);

    UserResponse userResponse = userDataService.findUser(new Username(username), new Token(token));

    return new ResponseEntity<>(userResponse, HttpStatus.OK);
  }
}
