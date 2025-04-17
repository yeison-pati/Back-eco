package com.itm.ecosurprise.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itm.ecosurprise.models.Producto;
import com.itm.ecosurprise.repositories.IProducto;

@Service
public class ProductoService {

    @Autowired
    private IProducto productoRepository;

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Producto obtenerXID(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto guardaProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void actualizarProducto(Producto producto) {
        
        productoRepository.save(producto);
    }

    public void eliminarProducto(int id) {
        productoRepository.deleteById(id);
    }

}
