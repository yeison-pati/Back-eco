package com.itm.ecosurprise.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itm.ecosurprise.models.Comerciante;
import com.itm.ecosurprise.models.Producto;
import com.itm.ecosurprise.repositories.IComerciante;
import com.itm.ecosurprise.repositories.IProducto;

@Service
public class ProductoService {

    @Autowired
    private IProducto productoRepository;
    @Autowired
    private IComerciante comercianteRepository;

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Producto obtenerXID(int idProducto) {
        return productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Comerciante no encontrado con ID: " + idProducto));
    }

    public Producto crear(int idComerciante, Producto producto) {
        Comerciante comerciante = comercianteRepository.findById(idComerciante)
                .orElseThrow(() -> new RuntimeException("Comerciante no encontrado con ID: " + idComerciante));
        producto.setComerciante(comerciante);
        return productoRepository.save(producto);
    }

    public Producto actualizar(Producto producto) {

        Producto productoExistente = productoRepository.findById(producto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + producto.getIdProducto()));
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setPrecio(producto.getPrecio());
        Comerciante comerciante = comercianteRepository.findById(producto.getComerciante().getIdUsuario())
                .orElseThrow(() -> new RuntimeException(
                        "Comerciante no encontrado con ID: " + producto.getComerciante().getIdUsuario()));
        productoExistente.setComerciante(comerciante);
        productoExistente.setPuntuaciones(producto.getPuntuaciones());
        return productoRepository.save(producto);

    }

    public String eliminar(int id) {
        productoRepository.deleteById(id);
        return "Producto eliminado con éxito";
    }
}
