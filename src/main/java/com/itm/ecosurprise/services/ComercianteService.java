package com.itm.ecosurprise.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.itm.ecosurprise.models.Comerciante;
import com.itm.ecosurprise.models.Producto;
import com.itm.ecosurprise.repositories.IComerciante;

public class ComercianteService {


@Autowired
private IComerciante comercianteRepository;

@Autowired
private ProductoService productoService;

public String saludar(){
    return "Hola desde el servicio de Comerciante";
}

public Producto crearProducto(Long idComerciante, Producto producto){
    producto.
    return productoService.guardaProducto(producto);
}



public List<Comerciante> obtenerComeriantes(){
    return comercianteRepository.findAll();
}

public Comerciante obtenerXID(Long id){
    return comercianteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Comerciante no encontrado con ID: " + id));
}



}
