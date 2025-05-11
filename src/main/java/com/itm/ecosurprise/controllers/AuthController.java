package com.itm.ecosurprise.controllers;

import com.itm.ecosurprise.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String correo = credentials.get("correo");
        String contrasena = credentials.get("contrasena");
        return authService.login(correo, contrasena);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, Object> userData) {
        return authService.register(userData);
    }
} 