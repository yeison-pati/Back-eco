package com.itm.ecosurprise.controllers;

import com.itm.ecosurprise.domain.port.in.JwtService;
import com.itm.ecosurprise.domain.port.in.LoginUseCase;
import com.itm.ecosurprise.domain.port.in.RegisterUserUseCase;
import com.itm.ecosurprise.infrastructure.web.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final RegisterUserUseCase registerUserUseCase;
    private final JwtService jwtService;

    public AuthController(LoginUseCase loginUseCase, RegisterUserUseCase registerUserUseCase, JwtService jwtService) {
        this.loginUseCase = loginUseCase;
        this.registerUserUseCase = registerUserUseCase;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Map<String, Object> response = loginUseCase.login(loginRequest.getCorreo(), loginRequest.getContrasena());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, Object> userData) {
        try {
            return ResponseEntity.ok(registerUserUseCase.register(userData));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            boolean isValid = jwtService.validateToken(token);

            if (isValid) {
                String newToken = jwtService.renewToken(token);
                return ResponseEntity.ok(Map.of(
                        "valid", true,
                        "token", newToken
                ));
            }
        }
        return ResponseEntity.ok(Map.of("valid", false));
    }
}
