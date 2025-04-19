package com.itm.ecosurprise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itm.ecosurprise.models.Consumidor;
import com.itm.ecosurprise.models.Direccion;
import com.itm.ecosurprise.models.Telefono;
import com.itm.ecosurprise.models.UsuarioDireccion;
import com.itm.ecosurprise.repositories.IConsumidor;
import com.itm.ecosurprise.repositories.IUsuarioDireccion;

@Service
public class ConsumidorService {

    @Autowired
    private IConsumidor consumidorRepository;
    @Autowired
    private TelefonoService telefonoService;
    @Autowired
    private DireccionService direccionService;
    @Autowired
    private UsuarioDireccionService usuarioDireccionService;
    @Autowired
    private IUsuarioDireccion usuarioDireccionRepository;

    public ResponseEntity<?> crear(Consumidor consumidor) {
        try {
            return ResponseEntity.ok(consumidorRepository.save(consumidor));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerTodos() {
        try {
            return ResponseEntity.ok(consumidorRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerXID(int id){
        try {
            return ResponseEntity.ok(consumidorRepository.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    public ResponseEntity<?> eliminar(int id) {
        try {
            consumidorRepository.deleteById(id);
            return ResponseEntity.ok("Comerciante eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    
    public ResponseEntity<?> actualizar(int id, Consumidor consumidor) {
        try {
            Consumidor consumidorexistente = consumidorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consumidor no encontrado con ID: " + id));
        consumidorexistente.setNombre(consumidor.getNombre());
        return ResponseEntity.ok(consumidorRepository.save(consumidorexistente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    public ResponseEntity<?> crearTelefono(int idConsumidor, Telefono telefono) {
        return ResponseEntity.ok(telefonoService.crear(idConsumidor, telefono));
    }

    public ResponseEntity<?> crearOrden(int idComerciante, Telefono telefono) {
        return ResponseEntity.ok(telefonoService.crear(idComerciante, telefono));
    }

    public ResponseEntity<?> crearDireccion(int idConsumidor, Direccion direccion) {
        UsuarioDireccion usuarioDireccion = usuarioDireccionService.crear(idConsumidor);
        usuarioDireccion.setDireccion(direccionService.crear(direccion));
        return ResponseEntity.ok(usuarioDireccionRepository.save(usuarioDireccion));
    }


}
