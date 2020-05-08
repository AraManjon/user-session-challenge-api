package services;

import com.kubikdata.services.TokenUsernameGenerator;
import io.jsonwebtoken.Claims;
import org.junit.Assert;
import org.junit.Test;

public class TokenUsernameGeneratorShould {

  @Test
  public void code_and_decode_token_by_username_correctly(){

    String username = "username";
    TokenUsernameGenerator tokenUsernameGenerator = new TokenUsernameGenerator();

    String token = tokenUsernameGenerator.code(username);
    String decodeToken = tokenUsernameGenerator.decode(token).getSubject();

    Assert.assertEquals(username, decodeToken);
  }
}
