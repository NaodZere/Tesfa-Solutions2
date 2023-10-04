//package com.asm.tesfaeribank.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtGenerator {
//    private String JWT_SECRET = "SECRET";
//
//    public String generateToken(Authentication authentication) {
//        String username = authentication.getName();
//        Date currentDate = new Date();
//        Date expiryDate = new Date(currentDate.getTime() + 700000);
//
//        String token = Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(currentDate)
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
//                .compact();
//
//        return token;
//
//    }
//
//
//    public String getUsernameFromJWT(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(JWT_SECRET)
//                .parseClaimsJws(token)
//                .getBody();
//        return claims.getSubject();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            throw new AuthenticationCredentialsNotFoundException("Token Expired or Incorrect");
//        }
//    }
//}
