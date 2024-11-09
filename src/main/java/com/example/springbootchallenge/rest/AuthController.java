package com.example.springbootchallenge.rest;

import com.example.springbootchallenge.auth.AuthRequest;
import com.example.springbootchallenge.auth.AuthResponse;
import com.example.springbootchallenge.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        // In a real application, you would authenticate the user here.
        // For simplicity, we are using hardcoded username and password validation.
        if ("user123".equals(authRequest.getUsername()) && "password".equals(authRequest.getPassword())) {
            // Generate JWT token
            // For now, added UserName in the token, but it should be ID
            String token = jwtUtil.generateToken(authRequest.getUsername());
            // Return token in the response object
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            // Invalid credentials
            return ResponseEntity.status(401).body(null);
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String authorizationHeader) {
        // Extract the token from the Authorization header
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body("Invalid Authorization header");
        }

        String token = authorizationHeader.substring(7); // Remove "Bearer " prefix

        // Validate the token
        if (jwtUtil.validateToken(token)) {
            String userId = jwtUtil.extractUserId(token);
            return ResponseEntity.ok("Token is valid. userId: " + userId);
        } else {
            return ResponseEntity.status(401).body("Invalid token");
        }
    }
}