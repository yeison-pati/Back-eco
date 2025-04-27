package com.itm.ecosurprise.services;

import com.itm.ecosurprise.dtos.CarritoDTO;
import com.itm.ecosurprise.dtos.ProductoDTO;
import com.itm.ecosurprise.models.Producto;
import com.itm.ecosurprise.repositories.IConsumidor;
import com.itm.ecosurprise.repositories.IProducto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import java.util.*;

@Service
public class CarritoService {
        @Autowired
        private IProducto productoRepository;
        @Autowired
        private IConsumidor consumidorRepository;

        // Mapa en memoria: ID de consumidor -> carrito
        private final Map<Integer, CarritoDTO> carritos = new HashMap<>();

        public CarritoDTO obtenerCarrito(int idConsumidor) {
                consumidorRepository.findById(idConsumidor)
                                .orElseThrow(() -> new RuntimeException(
                                                "Consumidor no encontrado con ID: " + idConsumidor));
                return carritos.computeIfAbsent(idConsumidor, id -> new CarritoDTO());
        }

        public ResponseEntity<?> agregarProducto(int idConsumidor, int idProducto, ProductoDTO productoCantidad){
                try {
                        
                        if (productoCantidad.getCantidad() <= 0 || productoCantidad.getCantidad() != NumberUtils.parseNumber(String.valueOf(productoCantidad.getCantidad()), Integer.class)) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cantidad no válida");
                        }
                        consumidorRepository.findById(idConsumidor)
                                        .orElseThrow(() -> new RuntimeException(
                                                        "Consumidor no encontrado con ID: " + idConsumidor));
                        CarritoDTO carrito = obtenerCarrito(idConsumidor);
                        Producto productoExistente = productoRepository.findById(idProducto)
                                        .orElseThrow(() -> new RuntimeException("error al agregar producto"));
                        
                        if (carrito.getProductos().stream()
                                        .anyMatch(producto -> producto.getId() == productoExistente.getIdProducto())) {
                                
                                carrito.getProductos().stream()
                                                .filter(producto -> producto.getId() == productoExistente.getIdProducto())
                                                .forEach(producto -> producto.setCantidad(producto.getCantidad() + productoCantidad.getCantidad()));
                                return ResponseEntity.ok("Cantidad actualizado en el carrito con éxito");
                                
                        }

                        ProductoDTO productoDTO = new ProductoDTO();
                        productoDTO.setId(productoExistente.getIdProducto());
                        productoDTO.setNombre(productoExistente.getNombre());
                        productoDTO.setPrecio(productoExistente.getPrecio());
                        productoDTO.setCantidad(productoCantidad.getCantidad());
                        carrito.getProductos().add(productoDTO);
                        return ResponseEntity.ok("Producto agregado al carrito con éxito");
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
                }
        }

        public ResponseEntity<?> limpiarCarrito(int idConsumidor) {
                try {
                        consumidorRepository.findById(idConsumidor)
                                        .orElseThrow(() -> new RuntimeException(
                                                        "Consumidor no encontrado con ID: " + idConsumidor));

                        carritos.remove(idConsumidor);
                        return ResponseEntity.ok("Carrito limpiado con éxito");
                } catch (Exception e) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
                }
        }

        public ResponseEntity<?> obtenerProductos(int idConsumidor) {
                try{
                calcularTotal(idConsumidor);
                CarritoDTO carrito = obtenerCarrito(idConsumidor);
                carrito.setTotal(calcularTotal(idConsumidor));
                return ResponseEntity.ok(carrito);
        } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        }

        public int calcularTotal(int idConsumidor) {
                consumidorRepository.findById(idConsumidor)
                                .orElseThrow(() -> new RuntimeException(
                                                "Consumidor no encontrado con ID: " + idConsumidor));

                CarritoDTO carrito = obtenerCarrito(idConsumidor);
                return carrito.getProductos().stream()
                                .mapToInt(producto -> producto.getPrecio() * producto.getCantidad())
                                .sum();
        }
}
