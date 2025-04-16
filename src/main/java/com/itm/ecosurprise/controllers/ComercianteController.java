package com.itm.ecosurprise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itm.ecosurprise.models.Comerciante;
import com.itm.ecosurprise.models.Consumidor;
import com.itm.ecosurprise.models.Producto;
import com.itm.ecosurprise.services.ComercianteService;

@RestController
@RequestMapping("/api/comerciante")
public class ComercianteController {

@Autowired
private ComercianteService comercianteService;

@PostMapping("/crearComerciante")
public Comerciante crearComerciante(Comerciante comerciante) {
    return comercianteService.crearComerciante(comerciante);
    
}

@PostMapping("/crearProducto")
public Producto crearProducto(Comerciante idComerciante, Producto producto) {
    return comercianteService.crearProducto(idComerciante, producto);

}


}
