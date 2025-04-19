package com.itm.ecosurprise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itm.ecosurprise.models.Comerciante;
import com.itm.ecosurprise.models.Direccion;
import com.itm.ecosurprise.models.Producto;
import com.itm.ecosurprise.models.Sede;
import com.itm.ecosurprise.models.Telefono;
import com.itm.ecosurprise.repositories.IComerciante;
import com.itm.ecosurprise.repositories.ISede;

@Service
public class ComercianteService {

    @Autowired
    private IComerciante comercianteRepository;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private TelefonoService telefonoService;
    @Autowired
    private DireccionService direccionService;
    @Autowired
    private SedeService sedeService;
    @Autowired
    private ISede sedeRepository;

    public ResponseEntity<?> crear(Comerciante comerciante) {
        try {
            return ResponseEntity.ok(comercianteRepository.save(comerciante));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerTodos() {
        try {
            return ResponseEntity.ok(comercianteRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerXID(int id) {
        try {
            return ResponseEntity.ok(comercianteRepository.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> eliminar(int id) {
        try {
            comercianteRepository.deleteById(id);
            return ResponseEntity.ok("Comerciante eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> actualizar(int id, Comerciante comerciante) {
        try {
            Comerciante comercianteExistente = comercianteRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));
            comercianteExistente.setNombre(comerciante.getNombre());
            return ResponseEntity.ok(comercianteRepository.save(comercianteExistente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    public ResponseEntity<?> crearProducto(int idComerciante, Producto producto) {
            return ResponseEntity.ok(productoService.crear(idComerciante, producto));
    }

    public ResponseEntity<?> crearTelefono(int idComerciante, Telefono telefono) {
        return ResponseEntity.ok(telefonoService.crear(idComerciante, telefono));
    }

    public ResponseEntity<?> crearSede(int idComerciante, Sede sede) {
        return ResponseEntity.ok(sedeService.crear(idComerciante, sede));
    }

    public ResponseEntity<?> crearDireccion(int idComerciante, int idSede, Direccion direccion) {
        Comerciante comerciante = comercianteRepository.findById(idComerciante)
                    .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));
            Sede sede = comerciante.getSedes().stream()
                    .filter(s -> s.getIdSede() == idSede)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Sede no encontrada"));
            sede.setDireccion(direccionService.crear(direccion));
        return ResponseEntity.ok(sedeRepository.save(sede));
    }
}
