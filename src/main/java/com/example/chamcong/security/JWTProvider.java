package com.example.chamcong.security;

import com.example.chamcong.entity.User;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class JWTProvider {
    private String jwtSecret = "Pf[F'`W5VR{S'!cw";
    private String zoomApiSecret = "zH2IqUgYKPIkMl7oVnPwJkbyUhRFP8mmRX0z";


    private long jwtExpirationInMs = 300000000; //50 minutes

    public String generateToken(User user) {
        return Jwts.builder().setSubject(user.getEmail()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs)).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }
    public String generateTokenEmail(String email) {
        return Jwts.builder().setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs)).signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }
//    public String generateZoomJWTToken() throws KeyStoreException {
//        String id = UUID.randomUUID().toString().replace("-", "");
//
//        return Jwts.builder()
//                .setId(id)
//                .setIssuer("VdKchwGgSzuAMu0hP2C4Ag")
//                .setIssuedAt(new Date((System.currentTimeMillis())))
//                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
//                .signWith(SignatureAlgorithm.HS256, key).compact();
//    }

    public String getEmailFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public Boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            return true;
        } catch (ExpiredJwtException ex) {
            return true;
        }
    }



}
