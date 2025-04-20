package com.itm.ecosurprise.services;

import com.itm.ecosurprise.dtos.CarritoDTO;
import com.itm.ecosurprise.dtos.ProductoDTO;
import com.itm.ecosurprise.models.Producto;
import com.itm.ecosurprise.repositories.IConsumidor;
import com.itm.ecosurprise.repositories.IProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new RuntimeException("Consumidor no encontrado con ID: " + idConsumidor));
        return carritos.computeIfAbsent(idConsumidor, id -> new CarritoDTO());
    }

    public String agregarProducto(int idConsumidor, int idProducto) {
        consumidorRepository.findById(idConsumidor)
                .orElseThrow(() -> new RuntimeException("Consumidor no encontrado con ID: " + idConsumidor));
        CarritoDTO carrito = obtenerCarrito(idConsumidor);
        Producto productoExistente = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("error al agregar producto"));
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(productoExistente.getIdProducto());
        productoDTO.setNombre(productoExistente.getNombre());
        productoDTO.setPrecio(productoExistente.getPrecio());
        carrito.getProductos().add(productoDTO);

        return "producto agregado al carrito con exito";
    }

    public String limpiarCarrito(int idConsumidor) {
        consumidorRepository.findById(idConsumidor)
                .orElseThrow(() -> new RuntimeException("Consumidor no encontrado con ID: " + idConsumidor));
        
        carritos.remove(idConsumidor);
        return "carrito limpiado con exito";
    }

    public List<ProductoDTO> obtenerProductos(int idConsumidor) {
        consumidorRepository.findById(idConsumidor)
                .orElseThrow(() -> new RuntimeException("Consumidor no encontrado con ID: " + idConsumidor));
        
        return obtenerCarrito(idConsumidor).getProductos();
    }

    public int calcularTotal(int idConsumidor){
        consumidorRepository.findById(idConsumidor)
                .orElseThrow(() -> new RuntimeException("Consumidor no encontrado con ID: " + idConsumidor));
        
        CarritoDTO carrito = obtenerCarrito(idConsumidor);
        return carrito.getProductos().stream()
                .mapToInt(ProductoDTO::getPrecio)
                .sum();
    }
}

