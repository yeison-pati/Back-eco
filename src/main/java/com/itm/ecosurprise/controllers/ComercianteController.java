package com.itm.ecosurprise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.itm.ecosurprise.models.Comerciante;
import com.itm.ecosurprise.models.Direccion;
import com.itm.ecosurprise.models.Producto;
import com.itm.ecosurprise.models.Sede;
import com.itm.ecosurprise.models.Telefono;
import com.itm.ecosurprise.services.ComercianteService;
import com.itm.ecosurprise.services.OrdenService;

@RestController
@RequestMapping("/api/comerciantes")
public class ComercianteController {

    @Autowired
    private ComercianteService comercianteService;
    @Autowired
    private OrdenService ordenService;

    @PostMapping(value = "/crear", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> crearComerciante(@RequestPart("comerciante") Comerciante comerciante,
                                              @RequestParam("imagen") MultipartFile imagen)
    {
        return comercianteService.crear(comerciante, imagen);
    }

    @PostMapping("/{idComerciante}/crearTelefono")
    public ResponseEntity<?> crearTelefono(@PathVariable int idComerciante, @RequestBody Telefono telefono) {
        return comercianteService.crearTelefono(idComerciante, telefono);
    }

    @PostMapping("/{idComerciante}/crearSede")
    public ResponseEntity<?> crearSede(@PathVariable int idComerciante, @RequestBody Sede sede) {
        return comercianteService.crearSede(idComerciante, sede);
    }

    @PostMapping("/{idComerciante}/sede/{idSede}/crearDireccion")
    public ResponseEntity<?> crearDireccion(@PathVariable int idComerciante, @PathVariable int idSede, @RequestBody Direccion direccion) {
        return comercianteService.crearDireccion(idComerciante, idSede, direccion);
    }

    @PostMapping(value = "/{idComerciante}/crearProducto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> crearProducto(@PathVariable int idComerciante, @RequestPart("producto") Producto producto,
     @RequestParam("imagen") MultipartFile imagen) {
        return comercianteService.crearProducto(idComerciante, producto, imagen);
    }

    @GetMapping("/todos")
    public ResponseEntity<?> obtenerTodos() {
        return comercianteService.obtenerTodos();
    }

    @GetMapping("/{idComerciante}")
    public ResponseEntity<?> obtenerXID(@PathVariable int idComerciante) {
        return comercianteService.obtenerXID(idComerciante);
    }

    @GetMapping("/{idComerciante}/productos/todos")
    public ResponseEntity<?> obtenerProductos(@PathVariable int idComerciante) {
        return comercianteService.obtenerProductos(idComerciante);
    }

    @GetMapping("/{idComerciante}/productos/{idProducto}")
    public ResponseEntity<?> obtenerProducto(@PathVariable int idComerciante,@PathVariable int idProducto) {
        return comercianteService.obtenerProducto(idComerciante, idProducto);
    }

    @GetMapping("/{idComerciante}/ordenes/todos")
    public ResponseEntity<?> obtenerOrdenes(@PathVariable int idComerciante) {
        return ordenService.obtenerTodos(idComerciante);
    }

    @GetMapping("/{idComerciante}/ordenes/{idOrden}")
    public ResponseEntity<?> obtenerOrden(@PathVariable int idComerciante, @PathVariable int idOrden) {

        return ordenService.obtenerOrden(idComerciante, idOrden);
    }

}
