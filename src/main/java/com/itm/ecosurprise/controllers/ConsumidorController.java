package com.itm.ecosurprise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.itm.ecosurprise.dtos.ProductoDTO;
import com.itm.ecosurprise.models.Consumidor;
import com.itm.ecosurprise.models.Direccion;
import com.itm.ecosurprise.models.Orden;
import com.itm.ecosurprise.models.Telefono;
import com.itm.ecosurprise.services.CarritoService;
import com.itm.ecosurprise.services.ConsumidorService;
import com.itm.ecosurprise.services.OrdenService;
import com.itm.ecosurprise.services.ProductoService;
import com.itm.ecosurprise.services.TelefonoService;
import com.itm.ecosurprise.services.UsuarioDireccionService;

@RestController
@RequestMapping("/api/consumidores")
public class ConsumidorController {

    @Autowired
    private ConsumidorService consumidorService;
    @Autowired
    private TelefonoService telefonoService;
    @Autowired
    private UsuarioDireccionService usuarioDireccionService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private CarritoService carritoService;
    @Autowired
    private OrdenService ordenService;


    @PostMapping(value = "/crear", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> crearComerciante(@RequestPart("consumidor") Consumidor consumidor, @RequestParam("imagen") MultipartFile imagen) {
        return consumidorService.crear(consumidor, imagen);
    }

    @PostMapping("/{idConsumidor}/crearTelefono")
    public ResponseEntity<?> crearTelefono(@PathVariable int idConsumidor, @RequestBody Telefono telefono) {
        return telefonoService.crear(idConsumidor, telefono);
    }

    @PostMapping("/{idConsumidor}/crearDireccion")
    public ResponseEntity<?> crearDireccion(@PathVariable int idConsumidor, @RequestBody Direccion direccion) {
        return usuarioDireccionService.crear(idConsumidor, direccion);
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
        return productoService.obtenerTodos(idConsumidor);
    }

    @GetMapping("/{idConsumidor}/productos/{idProducto}")
    public ResponseEntity<?> obtenerProducto(@PathVariable int idConsumidor,@PathVariable int idProducto) {
        return productoService.obtenerXID(idConsumidor, idProducto);
    }

    //agregar al carrito
    @PostMapping("/{idConsumidor}/productos/{idProducto}/agregar")
    public ResponseEntity<?> agregarAlCarrito(@PathVariable int idConsumidor,@PathVariable int idProducto, @RequestBody ProductoDTO productoCantidad) {
        return carritoService.agregarProducto(idConsumidor, idProducto, productoCantidad);
    }

    @GetMapping("/{idConsumidor}/carrito")
    public ResponseEntity<?> verCarrito(@PathVariable int idConsumidor) {
        return carritoService.obtenerProductos(idConsumidor);
    }
    
    @GetMapping("/{idConsumidor}/carrito/{productoId}/eliminar")
    public ResponseEntity<?> eliminarProductoCarrito(@PathVariable int idConsumidor, @PathVariable int productoId) {
        return carritoService.eliminarProducto(idConsumidor, productoId);
    }

    @GetMapping("/{idConsumidor}/carrito/limpiar")
    public ResponseEntity<?> limpiarCarrito(@PathVariable int idConsumidor) {
        return carritoService.limpiarCarrito(idConsumidor);
    }

    @PostMapping("/{idConsumidor}/carrito/ordenar")
    public ResponseEntity<?> crearOrden(@PathVariable int idConsumidor, @RequestBody Orden orden){
        return ordenService.crear(idConsumidor, orden);
    }
}