package com.itm.ecosurprise.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.itm.ecosurprise.models.Comerciante;
import com.itm.ecosurprise.models.Producto;
import com.itm.ecosurprise.services.ComercianteService;
import com.itm.ecosurprise.services.OrdenService;
import com.itm.ecosurprise.services.PreparacionOrdenes;
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
@RequestMapping("/api/comerciantes")
public class ComercianteController {

    /*
     * @Autowired inyecta las dependencias de los servicios ComercianteService y
     * OrdenService.
     * Esto permite utilizar los métodos de estos servicios en el controlador.
     */
    @Autowired
    private PreparacionOrdenes preparacionOrdenes;
    @Autowired
    private ComercianteService comercianteService;
    @Autowired
    private OrdenService ordenService;
    @Autowired
    private ProductoService productoService;

    @PostMapping(value = "/crear", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> crearComerciante(@RequestPart("usuario") Comerciante comerciante,
     @RequestPart("imagen") MultipartFile imagen, @RequestPart("rut") MultipartFile rut, @RequestPart("cc") MultipartFile cc) {
        return comercianteService.crear(comerciante, imagen, rut, cc);
    }

    @PostMapping(value = "/{idComerciante}/crearProducto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> crearProducto(
            @PathVariable int idComerciante,
            @RequestPart("producto") Producto producto,
            @RequestParam("imagen") MultipartFile imagen) {
            return productoService.crear(idComerciante, producto, imagen);
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
    public ResponseEntity<?> obtenerProducto(@PathVariable int idComerciante, @PathVariable int idProducto) {
        return comercianteService.obtenerProducto(idComerciante, idProducto);
    }

    @GetMapping("/{idComerciante}/ordenes/todos")
    public ResponseEntity<?> obtenerOrdenes(@PathVariable int idComerciante) {
        return ordenService.obtenerOrdenesComerciante(idComerciante);
    }

    @GetMapping("/{idComerciante}/ordenes/{idOrden}")
    public ResponseEntity<?> obtenerOrden(@PathVariable int idComerciante, @PathVariable int idOrden) {

        return ordenService.obtenerOrdenComerciante(idComerciante, idOrden);
    }

    @PostMapping("/{idComerciante}/ordenes/{idOrden}/confirmar")
    public ResponseEntity<?> confirmarOrden(@PathVariable int idComerciante, @PathVariable int idOrden) {
        return preparacionOrdenes.agregarOrden(idComerciante, idOrden);
    }

    @PostMapping("/{idComerciante}/ordenes/{idOrden}/cancelar")
    public ResponseEntity<?> cancelarOrden(@PathVariable int idComerciante, @PathVariable int idOrden) {
        return ordenService.cancelar(idComerciante, idOrden);
    }

    @GetMapping("/{idComerciante}/ordenes/preparacion")
    public ResponseEntity<?> orenesPreparacion(@PathVariable int idComerciante) {
        return preparacionOrdenes.obtenerOrdenes(idComerciante);
    }

    // obtener orden de la lista de ordenes en prep
    @GetMapping("/{idComerciante}/ordenes/preparacion/{idOrden}")
    public ResponseEntity<?> ordenPreparacion(@PathVariable int idComerciante, @PathVariable int idOrden) {
        return preparacionOrdenes.obtenerOrden(idComerciante, idOrden);
    }
}
