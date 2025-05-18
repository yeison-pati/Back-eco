package com.itm.ecosurprise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itm.ecosurprise.models.Consumidor;
import com.itm.ecosurprise.repositories.IConsumidor;

@Service
public class ConsumidorService {

    @Autowired
    private IConsumidor consumidorRepository; // Repositorio para gestionar la entidad Consumidor


    /**
     * Obtiene todos los consumidores registrados en la base de datos.
     * @return ResponseEntity con la lista de consumidores o el error si ocurre una excepción.
     */
    public ResponseEntity<?> obtenerTodos() {
        try {
            return ResponseEntity.ok(consumidorRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage()); // Manejo de errores
        }
    }

    /**
     * Obtiene un consumidor por su ID.
     * @param idConsumidor ID del consumidor a buscar.
     * @return ResponseEntity con el consumidor encontrado o el error si no se encuentra.
     */
    public ResponseEntity<?> obtenerXID(int idConsumidor){
        try {
            return ResponseEntity.ok(consumidorRepository.findById(idConsumidor)
            .orElseThrow(() -> new RuntimeException("Consumidor no encontrado con ID: " + idConsumidor)));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage()); // Manejo de errores
        }
    }

    /**
     * Elimina un consumidor por su ID.
     * @param id ID del consumidor a eliminar.
     * @return ResponseEntity con un mensaje de éxito o el error si ocurre una excepción.
     */
    public ResponseEntity<?> eliminar(int id) {
        try {
            consumidorRepository.deleteById(id);
            return ResponseEntity.ok("Comerciante eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // Manejo de errores
        }
    }

    /**
     * Actualiza los datos de un consumidor existente.
     * @param id ID del consumidor a actualizar.
     * @param consumidor Objeto con los nuevos datos del consumidor.
     * @return ResponseEntity con el consumidor actualizado o el error si ocurre una excepción.
     */
    public ResponseEntity<?> actualizar(int id, Consumidor consumidor) {
        try {
            Consumidor consumidorexistente = consumidorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consumidor no encontrado con ID: " + id));
            consumidorexistente.setNombre(consumidor.getNombre()); // Actualizar el nombre del consumidor
            return ResponseEntity.ok(consumidorRepository.save(consumidorexistente)); // Guardar el consumidor actualizado
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // Manejo de errores
        }
    }

}
