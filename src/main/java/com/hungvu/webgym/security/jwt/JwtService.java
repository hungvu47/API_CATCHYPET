package com.hungvu.webgym.security.jwt;

import com.hungvu.webgym.model.User;
import com.hungvu.webgym.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;
    @Value("${security.jwt.expiration}")
    private long jwtExpiration;

    private final TokenRepository tokenRepository;


    public JwtService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

   public <T> T extractClaim(String token, Function<Claims, T> resolver){
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
   }

   private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
   }

   public boolean isValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
       boolean validToken = tokenRepository
               .findByToken(token)
               .map(t -> !t.isLoggedOut())
               .orElse(false);

       return (username.equals(userDetails.getUsername())) && !isTokenExpired(token) && validToken;
   }

   private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
   }

   private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
   }

   public String generateToken(User user) {
        String token = Jwts
                .builder()
//                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
        return token;
   }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
