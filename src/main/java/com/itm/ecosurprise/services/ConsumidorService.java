package com.itm.ecosurprise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itm.ecosurprise.models.Consumidor;
import com.itm.ecosurprise.models.Direccion;
import com.itm.ecosurprise.models.Orden;
import com.itm.ecosurprise.models.Telefono;
import com.itm.ecosurprise.models.UsuarioDireccion;
import com.itm.ecosurprise.repositories.IConsumidor;
import com.itm.ecosurprise.repositories.IUsuarioDireccion;

@Service
public class ConsumidorService {

    @Autowired
    private IUsuarioDireccion usuarioDireccionRepository;
    @Autowired
    private IConsumidor consumidorRepository;
    @Autowired
    private TelefonoService telefonoService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private DireccionService direccionService;
    @Autowired
    private UsuarioDireccionService usuarioDireccionService;
    @Autowired
    private OrdenService ordenService;
    @Autowired
    private CarritoService carritoService;
    

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

    public ResponseEntity<?> obtenerXID(int idConsumidor){
        try {
            return ResponseEntity.ok(consumidorRepository.findById(idConsumidor)
            .orElseThrow(()-> new RuntimeException("Consumidor no encontrado con ID: " + idConsumidor )));
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
        try {
            return ResponseEntity.ok(telefonoService.crear(idConsumidor, telefono));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> crearOrden(int idComerciante, Telefono telefono) {

        try {
            return ResponseEntity.ok(telefonoService.crear(idComerciante, telefono));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    public ResponseEntity<?> crearDireccion(int idConsumidor, Direccion direccion) {
        try {
            UsuarioDireccion usuarioDireccion = usuarioDireccionService.crear(idConsumidor);
            usuarioDireccion.setDireccion(direccionService.crear(direccion));
            return ResponseEntity.ok(usuarioDireccionRepository.save(usuarioDireccion));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    public ResponseEntity<?> obtenerProductos(int idConsumidor){
        try{
            consumidorRepository.findById(idConsumidor)
            .orElseThrow(()-> new RuntimeException("Consumidor no encontrado con ID: " + idConsumidor));
            return ResponseEntity.ok(productoService.obtenerTodos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public  ResponseEntity<?> obtenerProducto(int idConsumidor, int idProducto){
        try{
            consumidorRepository.findById(idConsumidor)
            .orElseThrow(()-> new RuntimeException("consumidor no encontrado con ID: "+idConsumidor));
            return ResponseEntity.ok(productoService.obtenerXID(idProducto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> agregarAlCarrito(int idConsumidor, int idProducto) {
        try {
            return ResponseEntity.ok(carritoService.agregarProducto(idConsumidor, idProducto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> verCarrito(int idConsumidor) {
        try {
            return ResponseEntity.ok(carritoService.obtenerProductos(idConsumidor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> limpiarCarrito(int idConsumidor) {
        try {
            return ResponseEntity.ok(carritoService.limpiarCarrito(idConsumidor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    

    public ResponseEntity<?> crearOrden(int idConsumidor, Orden orden){
        try {
            return ResponseEntity.ok(ordenService.crear(idConsumidor, orden));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
