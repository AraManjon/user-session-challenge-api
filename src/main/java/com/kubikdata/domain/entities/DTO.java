package com.kubikdata.domain.entities;

import java.util.Date;

public interface DTO {
  class UserSession{

    public String username;
    public String token;
    public Date date;
  }
}