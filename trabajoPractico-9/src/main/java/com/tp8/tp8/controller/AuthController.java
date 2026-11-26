package com.tp8.tp8.controller;

import com.tp8.tp8.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        if (!"admin".equals(request.username()) || !"admin123".equals(request.password())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = jwtService.generateToken(request.username(), "USER");
        return ResponseEntity.ok(new LoginResponse(token, "Bearer"));
    }

    public record LoginRequest(String username, String password) {}
    public record LoginResponse(String token, String type) {}
}
