package com.itm.ecosurprise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itm.ecosurprise.models.Consumidor;
import com.itm.ecosurprise.models.Direccion;
import com.itm.ecosurprise.models.Telefono;
import com.itm.ecosurprise.services.ConsumidorService;

@RestController
@RequestMapping("/api/consumidor")
public class ConsumidorController {

    @Autowired
    private ConsumidorService consumidorService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearComerciante(@RequestBody Consumidor consumidor) {
        return consumidorService.crear(consumidor);
    }

    @PostMapping("/{idConsumidor}/crearTelefono")
    public ResponseEntity<?> crearTelefono(@PathVariable int idConsumidor, @RequestBody Telefono telefono) {
        return consumidorService.crearTelefono(idConsumidor, telefono);
    }

    @PostMapping("/{idConsumidor}/crearDireccion")
    public ResponseEntity<?> crearDireccion(@PathVariable int idConsumidor, @RequestBody Direccion direccion) {
        return consumidorService.crearDireccion(idConsumidor, direccion);
    }

    @GetMapping("/obtener/todos")
    public ResponseEntity<?> obtenerTodos() {
        return consumidorService.obtenerTodos();
    }

    @GetMapping("/obtener/{idConsumidor}")
    public ResponseEntity<?> obtenerXID(@PathVariable int idConsumidor) {
        return consumidorService.obtenerXID(idConsumidor);
    }
}