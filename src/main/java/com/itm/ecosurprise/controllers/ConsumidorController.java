package com.itm.ecosurprise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itm.ecosurprise.models.Consumidor;
import com.itm.ecosurprise.services.ConsumidorService;

@RestController
@RequestMapping("/api/consumidor")
public class ConsumidorController {

    @Autowired
    private ConsumidorService consumidorService;

    @RequestMapping("/saludo")
    public String saludar() {
        return consumidorService.saludar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getConsumidor(@PathVariable Long id) {
        try {
            Consumidor consumidor = consumidorService.getConsumidor(id);
            return ResponseEntity.ok(consumidor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/crear")
    public Consumidor saveConsumidor(@RequestBody Consumidor consumidor) {
        return consumidorService.saveConsumidor(consumidor);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarConsumidor(@PathVariable Long id) {
        return consumidorService.eliminarConsumidor(id);
    }

    @PutMapping("/actualizar/{id}")
    public String actualizarConsumidor(@PathVariable Long id, @RequestBody Consumidor consumidor) {
        return consumidorService.actualizarConsumidor(id, consumidor);
    }
}