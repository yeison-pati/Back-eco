package com.itm.ecosurprise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public Comerciante crearComerciante(@RequestBody Comerciante comerciante) {
    return comercianteService.crearComerciante(comerciante);
    
}

@PostMapping("/{id}/crearProducto")
public Producto crearProducto(@PathVariable  Comerciante idComerciante, @RequestBody  Producto producto) {
    return comercianteService.crearProducto(idComerciante, producto);

}


}
