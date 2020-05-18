package com.usersession.unit;

import com.usersession.domain.exceptions.UsernameIsNotValid;
import com.usersession.domain.valueobjects.Username;
import org.junit.Test;

public class UsernameShould {

  @Test(expected = UsernameIsNotValid.class)
  public void throw_an_error_when_username_is_empty(){
    new Username("");
  }

  @Test(expected = UsernameIsNotValid.class)
  public void throw_an_error_when_username_is_not_valid(){
    new Username("_@");
  }
}
