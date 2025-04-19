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

    /* Ejemplo: Uso de dto
    public Carrito obtenerCarrito(int id) {
        Telefono telefono = telefonoRepository.findById(id);
        Carrito carrito = new Carrito();
        carrito.setNumero(telefono.getNumero());
        return carrito;
    }*/

}