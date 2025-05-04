package com.itm.ecosurprise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itm.ecosurprise.config.LoginRequest;
import com.itm.ecosurprise.services.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        return usuarioService.login(loginRequest);
    }
}
