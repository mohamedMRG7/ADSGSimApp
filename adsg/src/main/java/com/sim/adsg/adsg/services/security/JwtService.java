package com.sim.adsg.adsg.services.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

  private static final String SECRET_KEY = "77397A24432646294A404E635266556A586E3272357538782F4125442A472D4B";

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  public String generateToken(
      Map<String, Object> extraClaims,
      UserDetails userDetails
  ) {
    return Jwts
        .builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && validateTokenSignature(token) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  private boolean validateTokenSignature(String token){
    try {
      Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  private boolean isTokenValid(String token) {
    return validateTokenSignature(token) && !isTokenExpired(token);
  }

  private String generateToken(
          Map<String, Object> extraClaims
  ) {
    return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject((String) extraClaims.get("username"))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
  }
  public static void main(String[] args) {
    JwtService jwtService = new JwtService();
    Map<String, Object> extraClaims = new HashMap<>();
    extraClaims.put("username","USER1660469318599");
    extraClaims.put("role","ADMIN");
    extraClaims.put("ID",13909);

    System.err.println(jwtService.generateToken(extraClaims));

//    //String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJJRCI6MTM5MDksInVzZXJuYW1lIjoibW9oYW1lZC5hdGVmIiwic3ViIjoibW9oYW1lZC5hdGVmIiwiaWF0IjoxNjc2MjcyNDM5LCJleHAiOjE2NzYyNzM4Nzl9.kvUg0ODJ29czxFx8coz7Zqflw00pxTVwz-1OJdPsPW8";
//    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQ1JFQVRPUiIsIklEIjoxMzkwOSwidXNlcm5hbWUiOiJtb2hhbWVkLmF0ZWYiLCJzdWIiOiJtb2hhbWVkLmF0ZWYiLCJpYXQiOjE2NzYyNzI0MzksImV4cCI6MTY3NjI3Mzg3OX0.tVyWh2Mbz8Jx5-tHmHbHVxMhwFAX55xu-uTO3bFH2J0";
//    boolean tokenValid = jwtService.isTokenValid(token);
//    System.out.println("tokenValid = " + tokenValid);
  }




}
