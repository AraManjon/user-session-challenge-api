package com.kubikdata.controller;

import com.kubikdata.controller.request.UserSessionRequest;
import com.kubikdata.domain.UserSessionService;
import com.kubikdata.services.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserSessionController {

    /**
     * this endpoint is needed to add a session id to a specific username
     * @param userSessionRequest
     * @return token
     */

    @Autowired
    TokenGenerator tokenGenerator;

    @PostMapping(value="/session")
    public ResponseEntity<String> addSession(@RequestBody UserSessionRequest userSessionRequest) {

        UserSessionService userSessionService = new UserSessionService(tokenGenerator);

        return new ResponseEntity<>(userSessionService.addSession(userSessionRequest.getUsername()), HttpStatus.OK);
    }
}
