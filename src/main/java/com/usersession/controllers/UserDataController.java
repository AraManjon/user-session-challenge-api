package com.usersession.controllers;

import com.usersession.controllers.response.UserDataResponse;
import com.usersession.domain.exceptions.TokenIsNotValid;
import com.usersession.domain.exceptions.UserSessionBadRequest;
import com.usersession.domain.exceptions.UserSessionNotFound;
import com.usersession.domain.valueobjects.Token;
import com.usersession.domain.UserDataService;
import com.usersession.domain.valueobjects.Username;
import com.usersession.domain.infrastructure.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * this class is used to return data info based on its user session token
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserDataController {

  @Autowired
  Repository repository;

  /**
   * @param username needed to find userSession
   * @param token    needed to find userSession
   * @return UserDataResponse
   * @throws UserSessionNotFound "Session not found"
   * @throws TokenIsNotValid     "Token not valid"
   * @throws RuntimeException    "Service unavailable"
   */
  @GetMapping(value = "/info/{username}/{token}")
  public ResponseEntity<Object> userInfoGet(@PathVariable String username, @PathVariable String token) {

    try {
      UserDataService userDataService = new UserDataService(repository);

      UserDataResponse userDataResponse = userDataService.findUser(new Username(username), new Token(token));
      return new ResponseEntity<>(userDataResponse, HttpStatus.OK);
    } catch (UserSessionNotFound exception) {

      return new ResponseEntity<>(exception.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    } catch (UserSessionBadRequest exception) {

      return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (RuntimeException exception) {

      return new ResponseEntity<>("Service unavailable", HttpStatus.SERVICE_UNAVAILABLE);
    }
  }
}
