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

import java.util.List;

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
            return ResponseEntity.ok(comercianteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Comerciante no encontrado con ID: " + id)));
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
        try {
            return ResponseEntity.ok(productoService.crear(idComerciante, producto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> crearTelefono(int idComerciante, Telefono telefono) {
        try {
            return ResponseEntity.ok(telefonoService.crear(idComerciante, telefono));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> crearSede(int idComerciante, Sede sede) {
        try {
            return ResponseEntity.ok(sedeService.crear(idComerciante, sede));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> crearDireccion(int idComerciante, int idSede, Direccion direccion) {

        try {
            Comerciante comerciante = comercianteRepository.findById(idComerciante)
                    .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));
            Sede sede = comerciante.getSedes().stream()
                    .filter(s -> s.getIdSede() == idSede)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Sede no encontrada"));
            sede.setDireccion(direccionService.crear(direccion));
            return ResponseEntity.ok(sedeRepository.save(sede));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    public ResponseEntity<?> obtenerProductos(int idComerciante) {
        try {
            Comerciante comerciante = comercianteRepository.findById(idComerciante)
                    .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));
            List<Producto> productos = comerciante.getProductos();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerProducto(int idComerciante, int idProducto) {
        try {
            Comerciante comerciante = comercianteRepository.findById(idComerciante)
                    .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));
            Producto producto = comerciante.getProductos().stream()
                    .filter(p -> p.getIdProducto() == idProducto)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("error al cargar el producto"));
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
