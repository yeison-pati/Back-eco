package com.itm.ecosurprise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.itm.ecosurprise.models.Comerciante;
import com.itm.ecosurprise.models.Direccion;
import com.itm.ecosurprise.models.Producto;
import com.itm.ecosurprise.models.Sede;
import com.itm.ecosurprise.models.Telefono;
import com.itm.ecosurprise.services.ComercianteService;

@RestController
@RequestMapping("/api/comerciante")
public class ComercianteController {

    @Autowired
    private ComercianteService comercianteService;

    @PostMapping("/crear")
    public ResponseEntity<?> crearComerciante(@RequestBody Comerciante comerciante) {
        return comercianteService.crear(comerciante);
    }

    @PostMapping("/{idComerciante}/crearTelefono")
    public ResponseEntity<?> crearTelefono(@PathVariable int idComerciante, @RequestBody Telefono telefono) {
        return comercianteService.crearTelefono(idComerciante, telefono);
    }

    @PostMapping("/{idComerciante}/crearSede")
    public ResponseEntity<?> crearSede(@PathVariable int idComerciante, @RequestBody Sede sede) {
        return comercianteService.crearSede(idComerciante, sede);
    }

    @PostMapping("/{idComerciante}/crearProducto")
    public ResponseEntity<?> crearProducto(@PathVariable int idComerciante, @RequestBody Producto producto) {
        return comercianteService.crearProducto(idComerciante, producto);
    }

    @PostMapping("/{idComerciante}/sede/{idSede}/crearDireccion")
    public ResponseEntity<?> crearDireccion(@PathVariable int idComerciante, @PathVariable int idSede, @RequestBody Direccion direccion) {
        return comercianteService.crearDireccion(idComerciante, idSede, direccion);
    }

    @GetMapping("/obtener/todos")
    public ResponseEntity<?> obtenerTodos() {
        return comercianteService.obtenerTodos();
    }

    @GetMapping("/obtener/{idComerciante}")
    public ResponseEntity<?> obtenerXID(@PathVariable int idComerciante) {
        return comercianteService.obtenerXID(idComerciante);
    }

    @GetMapping("/{idComerciante}/productos/obtener/todos")
    public ResponseEntity<?> obtenerProductos(@PathVariable int idComerciante) {
        return comercianteService.obtenerProductos(idComerciante);
    }

    @GetMapping("/{idComerciante}/productos/obtener/{idProducto}")
    public ResponseEntity<?> obtenerProducto(@PathVariable int idComerciante,@PathVariable int idProducto) {
        return comercianteService.obtenerProducto(idComerciante, idProducto);
    }
}
