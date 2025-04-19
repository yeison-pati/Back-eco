package com.itm.ecosurprise.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itm.ecosurprise.models.Telefono;
import com.itm.ecosurprise.models.Usuario;
import com.itm.ecosurprise.repositories.ITelefono;
import com.itm.ecosurprise.repositories.IUsuario;

@Service
public class TelefonoService {

    @Autowired
    private ITelefono telefonoRepository;
    @Autowired
    private IUsuario usuarioRepository;

    public List<Telefono> obtenerTodos() {
        return telefonoRepository.findAll();
    }

    public Telefono obtenerXID(int idTelefono) {
        return telefonoRepository.findById(idTelefono)
                .orElseThrow(() -> new RuntimeException("Comerciante no encontrado con ID: " + idTelefono));
    }

    public Telefono crear(int idUsuario, Telefono telefono) {
            Usuario usuario = usuarioRepository.findById(idUsuario)
                    .orElseThrow(() -> new RuntimeException("Comerciante no encontrado con ID: " + idUsuario));
            telefono.setUsuario(usuario);
            return telefonoRepository.save(telefono);
    }

    public Telefono actualizar(Telefono telefono) {
            return telefonoRepository.save(telefono);
    }

    public String eliminar(int id) {
            telefonoRepository.deleteById(id);
            return "Telefono eliminado con éxito";
    }

}

/*
 * 
    @Autowired
    private ITelefono telefonoRepository;
    @Autowired
    private IComerciante comercianteRepository;


    public Telefono crear(int idComerciante, Telefono producto) {
        Comerciante comerciante = comercianteRepository.findById(idComerciante)
                .orElseThrow(() -> new RuntimeException("Comerciante no encontrado con ID: " + idComerciante));
        producto.setComerciante(comerciante);
        return productoRepository.save(producto);
    }

    public Producto actualizar(Producto producto) {

        Producto productoExistente = productoRepository.findById(producto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + producto.getIdProducto()));
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setPrecio(producto.getPrecio());
        Comerciante comerciante = comercianteRepository.findById(producto.getComerciante().getIdUsuario())
                .orElseThrow(() -> new RuntimeException(
                        "Comerciante no encontrado con ID: " + producto.getComerciante().getIdUsuario()));
        productoExistente.setComerciante(comerciante);
        productoExistente.setPuntuaciones(producto.getPuntuaciones());
        return productoRepository.save(producto);

    }

    public ResponseEntity<?> eliminar(int id) {
        productoRepository.deleteById(id);
        return ResponseEntity.ok("Producto eliminado con éxito");
    }

 */