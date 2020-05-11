package com.kubikdata.utils;

import com.kubikdata.domain.dto.DTO;

import java.util.Date;

public class UserSessionDTOTestFactory {

  public static DTO.UserSession create(String username, String token) {

    DTO.UserSession userSession = new DTO.UserSession();
    userSession.username = username;
    userSession.token = token;
    userSession.date = new Date();

    return userSession;
  }
}
