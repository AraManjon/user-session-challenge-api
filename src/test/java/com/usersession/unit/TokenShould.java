package com.usersession.unit;

import com.usersession.domain.exceptions.TokenIsNotValid;
import com.usersession.domain.valueobjects.Token;
import org.junit.Test;

public class TokenShould {

  @Test(expected = TokenIsNotValid.class)
  public void throw_an_error_when_token_is_empty(){
    new Token("");
  }

  @Test(expected = TokenIsNotValid.class)
  public void throw_an_error_when_token_is_not_valid(){
    new Token("token");
  }
}
