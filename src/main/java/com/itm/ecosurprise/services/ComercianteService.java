package com.itm.ecosurprise.services;
import java.util.List;

import com.itm.ecosurprise.models.Consumidor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itm.ecosurprise.models.Comerciante;
import com.itm.ecosurprise.models.Producto;
import com.itm.ecosurprise.repositories.IComerciante;

@Service
public class ComercianteService {

@Autowired
private IComerciante comercianteRepository;
@Autowired
private ProductoService productoService;


public String saludar(){
    return "Hola desde el servicio de Comerciante";
}

    public ResponseEntity<?> crearProducto(int idComerciante, Producto producto) {
        try {
            // Busca el Comerciante por su ID
            Comerciante comerciante = comercianteRepository.findById(idComerciante)
                    .orElseThrow(() -> new RuntimeException("Comerciante no encontrado con ID: " + idComerciante));
            producto.setComerciante(comerciante);
            return ResponseEntity.ok(productoService.crearProducto(producto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> crearComerciante(Comerciante comerciante){
        try {
            return ResponseEntity.ok(comercianteRepository.save(comerciante));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?>  obtenerComeriantes(){
        try {
            return ResponseEntity.ok(comercianteRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerXID(int id){
        try {
            return ResponseEntity.ok(comercianteRepository.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> eliminarComerciante(int id) {
        try {
            comercianteRepository.deleteById(id);
            return ResponseEntity.ok("Comerciante eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
