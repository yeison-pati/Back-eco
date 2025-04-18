package com.itm.ecosurprise.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itm.ecosurprise.models.Producto;
import com.itm.ecosurprise.repositories.IProducto;

@Service
public class ProductoService {

    @Autowired
    private IProducto productoRepository;

    public ResponseEntity<?> obtenerProductos() {
        try {
            return ResponseEntity.ok(productoRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    public ResponseEntity<?> obtenerXID(int id) {
        try {
            return ResponseEntity.ok(productoRepository.findById(id).orElse(null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> crearProducto(Producto producto) {
        try {
            return ResponseEntity.ok(productoRepository.save(producto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> actualizarProducto(Producto producto) {

        try {
            return ResponseEntity.ok(productoRepository.save(producto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    public ResponseEntity<?> eliminarProducto(int id) {
        try {
            productoRepository.deleteById(id);
            return ResponseEntity.ok("Producto eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
