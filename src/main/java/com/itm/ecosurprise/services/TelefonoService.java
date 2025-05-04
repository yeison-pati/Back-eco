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

    /**
     * Obtiene una lista de todos los teléfonos almacenados en el repositorio.
     *
     * @return Una lista de objetos `Telefono`.
     */
    public List<Telefono> obtenerTodos() {
        return telefonoRepository.findAll();
    }

    /**
     * Obtiene un teléfono específico por su ID.
     *
     * @param idTelefono El ID del teléfono a obtener.
     * @return El objeto `Telefono` correspondiente al ID proporcionado.
     * @throws RuntimeException Si no se encuentra el teléfono con el ID dado.
     */
    public Telefono obtenerXID(int idTelefono) {
        return telefonoRepository.findById(idTelefono)
                .orElseThrow(() -> new RuntimeException("Telefono no encontrado"));
    }

    public Telefono crear(int idUsuario, Telefono telefono) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Error al obtener usuario"));

        if (usuario.getTelefono() != null) {
            throw new RuntimeException("El " + usuario.getRol() + " ya tiene un teléfono asignado.");
        }

        // Asignar ambos lados
        telefono.setUsuario(usuario);

        return telefonoRepository.save(telefono);
    }

    /**
     * Actualiza la información de un teléfono existente.
     *
     * @param telefono El objeto `Telefono` con los nuevos valores a actualizar.
     * @return El objeto `Telefono` actualizado.
     */
    public Telefono actualizar(Telefono telefono) {
        return telefonoRepository.save(telefono);
    }

    /**
     * Elimina un teléfono por su ID.
     *
     * @param id El ID del teléfono a eliminar.
     * @return Un mensaje indicando que el teléfono ha sido eliminado con éxito.
     */
    public String eliminar(int id) {
        telefonoRepository.deleteById(id);
        return "Teléfono eliminado con éxito";
    }

    /*
     * Ejemplo de uso de DTO (Data Transfer Object):
     * public Carrito obtenerCarrito(int id) {
     * Telefono telefono = telefonoRepository.findById(id);
     * Carrito carrito = new Carrito();
     * carrito.setNumero(telefono.getNumero());
     * return carrito;
     * }
     */
}
