package com.itm.ecosurprise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.itm.ecosurprise.dtos.ProductoDTO;
import com.itm.ecosurprise.models.Consumidor;
import com.itm.ecosurprise.models.Orden;
import com.itm.ecosurprise.services.CarritoService;
import com.itm.ecosurprise.services.ConsumidorService;
import com.itm.ecosurprise.services.OrdenService;
import com.itm.ecosurprise.services.ProductoService;


/*
 * @RestController indica que esta clase es un controlador REST que maneja solicitudes HTTP.
 * @RequestMapping("/api/comerciantes") define la ruta base para todas las solicitudes de este controlador.
 * @PathVariable se usa para extraer valores de la URL.
 * @RequestBody se usa para extraer el cuerpo de la solicitud HTTP.
 * @RequestParam se usa para extraer parámetros de la solicitud HTTP.
 * @RequestPart se usa para extraer partes de una solicitud multipart/form-data.
 * mediatype.MULTIPART_FORM_DATA_VALUE indica que el controlador acepta solicitudes con datos de formulario multipart.
 */
@RestController
@RequestMapping("/api/consumidores")
public class ConsumidorController {

    @Autowired
    private ConsumidorService consumidorService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private CarritoService carritoService;
    @Autowired
    private OrdenService ordenService;


    @PostMapping(value = "/crear", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> crearComerciante(@RequestPart("usuario") Consumidor consumidor, @RequestPart("imagen") MultipartFile imagen) {
        System.out.println(imagen);
        return consumidorService.crear(consumidor, imagen);
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
        return carritoService.agregarProducto(idConsumidor, idProducto, productoCantidad.getCantidad());
    }

    @GetMapping("/{idConsumidor}/carrito")
    public ResponseEntity<?> verCarrito(@PathVariable int idConsumidor) {
        return carritoService.obtenerProductos(idConsumidor);
    }
    
    @GetMapping("/{idConsumidor}/carrito/{productoId}/eliminar")
    public ResponseEntity<?> eliminarProductoCarrito(@PathVariable int idConsumidor, @PathVariable int productoId) {
        return carritoService.eliminarProducto(idConsumidor, productoId);
    }

    @GetMapping("/{idConsumidor}/carrito/{idProducto}/cambiarCantidad")
    public ResponseEntity<?> cambiarCatidadProducto(@PathVariable int idConsumidor,@PathVariable int idProducto, @RequestBody ProductoDTO productoCantidad) {
        return carritoService.cambiarCantidadProducto(idConsumidor, idProducto, productoCantidad.getCantidad());
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