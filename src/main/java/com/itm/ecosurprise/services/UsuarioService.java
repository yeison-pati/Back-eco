package com.itm.ecosurprise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itm.ecosurprise.config.LoginRequest;
import com.itm.ecosurprise.models.Usuario;
import com.itm.ecosurprise.repositories.IComerciante;
import com.itm.ecosurprise.repositories.IConsumidor;
import com.itm.ecosurprise.repositories.IUsuario;

@Service
public class UsuarioService {

    @Autowired
    private IUsuario usuarioRepository;
    @Autowired
    private IConsumidor consumidorRepository;
    @Autowired
    private IComerciante comercianteRepository;

    public ResponseEntity<?> login(LoginRequest loginRequest) {
        try {
            Usuario usuario = usuarioRepository.findByCorreoJPQL(loginRequest.getCorreo())
                    .orElseThrow(() -> new RuntimeException("correo no encontrado"));

            if (!usuario.getContrasena().equals(loginRequest.getContrasena())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
            }
            switch(usuario.getRol()){
                case "CONSUMIDOR":
                    return ResponseEntity.ok(consumidorRepository.findById(usuario.getIdUsuario()));
                case "COMERCIANTE":
                    return ResponseEntity.ok(comercianteRepository.findById(usuario.getIdUsuario()));
                default:
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error estableciendo usuario");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
