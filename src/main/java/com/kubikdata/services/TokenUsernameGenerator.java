package com.kubikdata.services;

import com.kubikdata.domain.infrastructure.TokenGenerator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

/**
 * this class create user session token
 */
@Component
public class TokenUsernameGenerator implements TokenGenerator {

  private final String secretKey = "this is my secret key for token generator";

  /**
   * this class create token by username
   * @param username required to create token
   * @return token
   */
  @Override
  public String code(String username) {

    Date currentDate = new Date(System.currentTimeMillis());
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    Key signingKey = new SecretKeySpec(secretKey.getBytes(), signatureAlgorithm.getJcaName());

    return Jwts.builder()
        .setIssuedAt(currentDate)
        .setSubject(username)
        .signWith(signingKey)
        .compact();
  }

  /**
   * this method check if token is match with username
   * @param token to decode
   * @param username to match with token
   * @return boolean
   */
  public Boolean decode(String token, String username) {
    return Jwts.parser()
        .setSigningKey(this.secretKey.getBytes())
        .parseClaimsJws(token)
        .getBody().getSubject().equals(username);
  }
}
