package services;

import com.kubikdata.services.TokenUsernameGenerator;
import org.junit.Assert;
import org.junit.Test;

public class TokenUsernameGeneratorShould {

  @Test
  public void code_and_decode_token_by_username_correctly(){

    String username = "username";
    TokenUsernameGenerator tokenUsernameGenerator = new TokenUsernameGenerator();
    String token = tokenUsernameGenerator.code(username);

    Assert.assertEquals(username, tokenUsernameGenerator.decode(token));
  }
}
