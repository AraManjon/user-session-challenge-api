package com.kubikdata.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class TokenUsernameGenerator implements TokenGenerator {

  private final String secretKey = "this is my secret key for token generator";

  @Override
  public String code(String username) {

    Date currentDate = new Date(System.currentTimeMillis());
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    Key signingKey = new SecretKeySpec(secretKey.getBytes(), signatureAlgorithm.getJcaName());

    JwtBuilder builder = Jwts.builder()
        .setIssuedAt(currentDate)
        .setSubject(username)
        .signWith(signingKey);

    return builder.compact();
  }

  public Claims decode(String username) {

    return Jwts.parser()
        .setSigningKey(this.secretKey.getBytes())
        .parseClaimsJws(username)
        .getBody();
  }
}
