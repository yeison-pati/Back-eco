package com.itm.ecosurprise.controllers;

import com.itm.ecosurprise.domain.port.in.ComercianteUseCase;
import com.itm.ecosurprise.domain.port.in.ConsumidorUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final ConsumidorUseCase consumidorUseCase;
    private final ComercianteUseCase comercianteUseCase;

    public AdminController(ConsumidorUseCase consumidorUseCase, ComercianteUseCase comercianteUseCase) {
        this.consumidorUseCase = consumidorUseCase;
        this.comercianteUseCase = comercianteUseCase;
    }

    @GetMapping("/consumidores/todos")
    public ResponseEntity<?> obtenerConsumidoress() {
        return ResponseEntity.ok(consumidorUseCase.getAll());
    }

    @GetMapping("/consumidores/{idConsumidor}")
    public ResponseEntity<?> obtenerConsumidoresPorId(@PathVariable int idConsumidor) {
        return ResponseEntity.ok(consumidorUseCase.getById(idConsumidor));
    }

    @GetMapping("/comerciantes/todos")
    public ResponseEntity<?> obtenerTodos() {
        return ResponseEntity.ok(comercianteUseCase.getAll());
    }

    @GetMapping("/comerciantes/{idComerciante}")
    public ResponseEntity<?> obtenerXID(@PathVariable int idComerciante) {
        return ResponseEntity.ok(comercianteUseCase.getById(idComerciante));
    }
}
