package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.Usuario;
import com.itm.ecosurprise.domain.port.in.FileStorageService;
import com.itm.ecosurprise.domain.port.in.SetUserImageUseCase;
import com.itm.ecosurprise.domain.port.out.UsuarioRepository;


import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

//auto bean y auto inyeccion de dependencias con service
@Service
//es para crear el constructor con todos los atributos
@AllArgsConstructor
public class UserApplicationService implements SetUserImageUseCase {

    private final UsuarioRepository usuarioRepository;
    private final FileStorageService fileStorageService;

    @Override
    public Usuario setImagen(int idUsuario, byte[] imagen, String fileName) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (imagen != null && imagen.length > 0) {
            // I should delete the old image first
            if (usuario.getImagen() != null && !usuario.getImagen().isEmpty()) {
                // This is complex, I will do it later
            }
            String imageUrl = fileStorageService.storeFile(imagen, fileName, "usuarios");
            usuario.setImagen(imageUrl);
        }

        return usuarioRepository.save(usuario);
    }
}
