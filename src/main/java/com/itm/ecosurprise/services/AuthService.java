package com.itm.ecosurprise.services;

import com.itm.ecosurprise.models.Usuario;
import com.itm.ecosurprise.models.Consumidor;
import com.itm.ecosurprise.models.Comerciante;
import com.itm.ecosurprise.models.Repartidor;
import com.itm.ecosurprise.repositories.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private final IUsuario usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(IUsuario usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public ResponseEntity<?> login(String correo, String contrasena) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(correo);
        
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }

        Usuario usuario = usuarioOpt.get();
        if (!passwordEncoder.matches(contrasena, usuario.getContrasena())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("rol", usuario.getRol());
        response.put("id", usuario.getIdUsuario());
        response.put("correo", usuario.getCorreo());

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> register(Map<String, Object> userData) {
        String rol = (String) userData.get("rol");
        Usuario usuario;

        switch (rol.toUpperCase()) {
            case "CONSUMIDOR":
                usuario = new Consumidor();
                break;
            case "COMERCIANTE":
                usuario = new Comerciante();
                break;
            case "REPARTIDOR":
                usuario = new Repartidor();
                break;
            default:
                return ResponseEntity.badRequest().body("Rol no válido");
        }

        if (usuarioRepository.findByCorreo((String) userData.get("correo")).isPresent()) {
            return ResponseEntity.badRequest().body("El correo ya está registrado");
        }

        usuario.setNombre((String) userData.get("nombre"));
        usuario.setCorreo((String) userData.get("correo"));
        usuario.setContrasena(passwordEncoder.encode((String) userData.get("contrasena")));
        usuario.setRol(rol);

        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }
} 