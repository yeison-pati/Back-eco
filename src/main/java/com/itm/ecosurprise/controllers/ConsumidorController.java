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
import com.itm.ecosurprise.models.Orden;
import com.itm.ecosurprise.models.Telefono;
import com.itm.ecosurprise.services.ConsumidorService;

@RestController
@RequestMapping("/api/consumidores")
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

    @GetMapping("/todos")
    public ResponseEntity<?> obtenerTodos() {
        return consumidorService.obtenerTodos();
    }

    @GetMapping("/{idConsumidor}")
    public ResponseEntity<?> obtenerXID(@PathVariable int idConsumidor) {
        return consumidorService.obtenerXID(idConsumidor);
    }

    @GetMapping("/{idConsumidor}/productos/todos")
    public ResponseEntity<?> obtenerProductos(@PathVariable int idConsumidor) {
        return consumidorService.obtenerProductos(idConsumidor);
    }

    @GetMapping("/{idConsumidor}/productos/{idProducto}")
    public ResponseEntity<?> obtenerProducto(@PathVariable int idConsumidor,@PathVariable int idProducto) {
        return consumidorService.obtenerProducto(idConsumidor, idProducto);
    }

    //agregar al carrito
    @PostMapping("/{idConsumidor}/productos/{idProducto}/agregar")
    public ResponseEntity<?> agregarAlCarrito(@PathVariable int idConsumidor,@PathVariable int idProducto) {
        return consumidorService.agregarAlCarrito(idConsumidor, idProducto);
    }

    @GetMapping("/{idConsumidor}/carrito")
    public ResponseEntity<?> verCarrito(@PathVariable int idConsumidor) {
        return consumidorService.verCarrito(idConsumidor);
    }

    @GetMapping("/{idConsumidor}/carrito/limpiar")
    public ResponseEntity<?> limpiarCarrito(@PathVariable int idConsumidor) {
        return consumidorService.limpiarCarrito(idConsumidor);
    }

    @PostMapping("/{idConsumidor}/carrito/ordenar")
    public ResponseEntity<?> crearOrden(@PathVariable int idConsumidor, @RequestBody Orden orden){
        return consumidorService.crearOrden(idConsumidor, orden);
    }
}