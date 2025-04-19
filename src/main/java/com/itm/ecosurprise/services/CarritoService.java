package com.itm.ecosurprise.services;

import com.itm.ecosurprise.dtos.CarritoDTO;
import com.itm.ecosurprise.dtos.ProductoDTO;
import com.itm.ecosurprise.models.Producto;
import com.itm.ecosurprise.repositories.IProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarritoService {
    @Autowired
    private IProducto productoRepository;

    // Mapa en memoria: ID de consumidor -> carrito
    private final Map<Integer, CarritoDTO> carritos = new HashMap<>();

    public CarritoDTO obtenerCarrito(int idConsumidor) {
        return carritos.computeIfAbsent(idConsumidor, id -> new CarritoDTO());
    }

    public void agregarProducto(int idConsumidor, int idProducto) {
        CarritoDTO carrito = obtenerCarrito(idConsumidor);
        Producto productoExistente = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("error al agregar producto"));
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(productoExistente.getIdProducto());
        productoDTO.setNombre(productoExistente.getNombre());
        productoDTO.setPrecio(productoExistente.getPrecio());
        carrito.getProductos().add(productoDTO);
    }

    public String limpiarCarrito(int idConsumidor) {
        carritos.remove(idConsumidor);
        return "carrito limpiado con exito";
    }

    public List<ProductoDTO> obtenerProductos(int idConsumidor) {
        return obtenerCarrito(idConsumidor).getProductos();
    }
}

