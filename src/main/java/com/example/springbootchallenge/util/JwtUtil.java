package com.example.springbootchallenge.util;

import com.example.springbootchallenge.service.TestService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final Logger logger = Logger.getLogger(JwtUtil.class.getName());


    @Value("${springboot.auth.workflow.jwt.secret}")
    private String secretKey;

    // Method to generate JWT token
    public String generateToken(String userId) {
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + 1000 * 60 * 60; // 1 hour expiration
        Date exp = new Date(expMillis);

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Method to validate the JWT token
    public boolean validateToken(String token) {
        try {
            // Add more validation if needed!
            String[] parts = token.split("\\.");
            if(parts.length != 3){
                logger.error("Unexpected token length");
                return false;
            }
            // Alphanumeric check
            // extract info by base 64Decoding

            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            String userId = claims.getSubject();

            // Extract the expiration date
            Date expiration = claims.getExpiration();

            // Print or use the extracted values
            System.out.println("User ID: " + userId);
            System.out.println("Expiration: " + expiration);

            return true;
        } catch (SignatureException e) {
            // Invalid token
            logger.error("Singature in the token are invalid", e);
            return false;
        } catch (Exception e) {
            // Other errors
            logger.error("Something unexpected happened", e);
            return false;
        }
    }

    // Method to extract username from the token
    public String extractUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}