package com.itm.ecosurprise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itm.ecosurprise.models.Direccion;
import com.itm.ecosurprise.models.Usuario;
import com.itm.ecosurprise.models.UsuarioDireccion;
import com.itm.ecosurprise.repositories.IUsuario;
import com.itm.ecosurprise.repositories.IUsuarioDireccion;

@Service
public class UsuarioDireccionService {
;
    @Autowired
    private IUsuarioDireccion usuarioDireccionRepository;
    @Autowired
    private DireccionService direccionService;
    @Autowired
    private IUsuario usuarioRepository;

    

    public UsuarioDireccion crear(int idUsuario, Direccion direccion) {
            // Crear nueva instancia de UsuarioDireccion
            UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
            
            // Obtener el consumidor (usuario) desde el repositorio
            Usuario usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            
            // Asignar el usuario y la dirección a la entidad UsuarioDireccion
            usuarioDireccion.setUsuario(usuario);
            usuarioDireccion.setDireccion(direccionService.crear(direccion)); // Crear dirección
            
            // Guardar la relación en el repositorio y devolver la respuesta
            return usuarioDireccionRepository.save(usuarioDireccion);
    }
}
