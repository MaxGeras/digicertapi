package com.example.digicertdemoapi.utill;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.catalina.User;

import java.nio.file.AccessDeniedException;
import java.util.Date;

public class JwtUtils {

  private static final String secret = "This_is_secret";

  public String generateJwt(final User user){

    long milliTime = System.currentTimeMillis();
    long expiryDuration = 60 * 60;
    long expiryTime = milliTime + expiryDuration * 1000;

    Date issuedAt = new Date(milliTime);
    Date expiryAt = new Date(expiryTime);

    // claims
    Claims claims = Jwts.claims()
        .setIssuer(user.getName())
        .setIssuedAt(issuedAt)
        .setExpiration(expiryAt);

    // optional claims
    claims.put("type", user.getPassword());
    claims.put("name", user.getName());
    claims.put("emailId", user.getUsername());

    // generate jwt using claims
    return Jwts.builder()
        .setClaims(claims)
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }

  public static Claims verify(String authorization) throws Exception {

    try {
      return Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
    } catch(Exception e) {
      throw new AccessDeniedException("Access Denied");
    }

  }
}
