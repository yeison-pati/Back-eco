package com.itm.ecosurprise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.itm.ecosurprise.models.Comerciante;
import com.itm.ecosurprise.models.Producto;
import com.itm.ecosurprise.services.ComercianteService;

@RestController
@RequestMapping("/api/comerciante")
public class ComercianteController {

    @Autowired
    private ComercianteService comercianteService;

    @PostMapping("/crearComerciante")
    public ResponseEntity<?> crearComerciante(@RequestBody Comerciante comerciante) {
        return comercianteService.crearComerciante(comerciante);
    }

    @PostMapping("/{idComerciante}/crearProducto")
    public ResponseEntity<?> crearProducto(@PathVariable  int idComerciante, @RequestBody  Producto producto) {
        return comercianteService.crearProducto(idComerciante, producto);
    }
}
