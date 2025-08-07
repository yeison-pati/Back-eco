package com.itm.ecosurprise.controllers;

import com.itm.ecosurprise.domain.model.Producto;
import com.itm.ecosurprise.domain.model.Telefono;
import com.itm.ecosurprise.domain.port.in.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/comerciantes")
public class ComercianteController {

    private final PreparationOrdersUseCase preparationOrdersUseCase;
    private final ComercianteUseCase comercianteUseCase;
    private final OrderUseCase orderUseCase;
    private final TelefonoUseCase telefonoUseCase;
    private final ProductUseCase productUseCase;
    private final SetUserImageUseCase setUserImageUseCase;


    public ComercianteController(PreparationOrdersUseCase preparationOrdersUseCase, ComercianteUseCase comercianteUseCase, OrderUseCase orderUseCase, TelefonoUseCase telefonoUseCase, ProductUseCase productUseCase, SetUserImageUseCase setUserImageUseCase) {
        this.preparationOrdersUseCase = preparationOrdersUseCase;
        this.comercianteUseCase = comercianteUseCase;
        this.orderUseCase = orderUseCase;
        this.telefonoUseCase = telefonoUseCase;
        this.productUseCase = productUseCase;
        this.setUserImageUseCase = setUserImageUseCase;
    }

    @PostMapping(value = "/{idComerciante}/establecerImagen")
    public ResponseEntity<?> setImagen(@PathVariable int idComerciante, @RequestParam("imagen") MultipartFile imagen) {
        try {
            return ResponseEntity.ok(setUserImageUseCase.setImagen(idComerciante, imagen.getBytes(), imagen.getOriginalFilename()));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error al leer la imagen");
        }
    }

    @PostMapping("/{idComerciante}/crearTelefono")
    public ResponseEntity<?> crearTelefono(@PathVariable int idComerciante, @RequestBody Telefono telefono) {
        return ResponseEntity.ok(telefonoUseCase.create(telefono));
    }

    @PostMapping(value = "/{idComerciante}/crearProducto")
    public ResponseEntity<?> crearProducto(@PathVariable int idComerciante, @RequestParam("producto") String productoJson, @RequestParam("imagen") MultipartFile imagen) {
        try {
            // The service should handle the JSON parsing
            Producto producto = new com.fasterxml.jackson.databind.ObjectMapper().readValue(productoJson, Producto.class);
            return ResponseEntity.ok(productUseCase.createProduct(idComerciante, producto, imagen.getBytes(), imagen.getOriginalFilename()));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error al leer la imagen o el JSON");
        }
    }

    @GetMapping("/{idComerciante}/productos/todos")
    public ResponseEntity<?> obtenerProductos(@PathVariable int idComerciante) {
        return ResponseEntity.ok(comercianteUseCase.getProducts(idComerciante));
    }

    @GetMapping("/{idComerciante}/productos/{idProducto}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable int idComerciante, @PathVariable int idProducto) {
        return ResponseEntity.ok(comercianteUseCase.getProductById(idComerciante, idProducto));
    }

    @PostMapping("/{idComerciante}/actualizarProducto/{idProducto}")
    public ResponseEntity<?> actualizarProducto(@PathVariable int idComerciante, @PathVariable int idProducto, @RequestParam("producto") String productoJson, @RequestParam("imagen") MultipartFile imagen) {
        try {
            Producto producto = new com.fasterxml.jackson.databind.ObjectMapper().readValue(productoJson, Producto.class);
            return ResponseEntity.ok(productUseCase.updateProduct(idComerciante, idProducto, producto, imagen.getBytes(), imagen.getOriginalFilename()));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error al leer la imagen o el JSON");
        }
    }

    @GetMapping("/{idComerciante}/ordenes/todos")
    public ResponseEntity<?> obtenerOrdenes(@PathVariable int idComerciante) {
        return ResponseEntity.ok(orderUseCase.getAllByComerciante(idComerciante));
    }

    @GetMapping("/{idComerciante}/ordenes/{idOrden}")
    public ResponseEntity<?> obtenerOrden(@PathVariable int idComerciante, @PathVariable int idOrden) {
        return ResponseEntity.ok(orderUseCase.getProductsByOrderAndComerciante(idComerciante, idOrden));
    }

    @PostMapping("/{idComerciante}/ordenes/{idOrden}/confirmar")
    public ResponseEntity<?> confirmarOrden(@PathVariable int idComerciante, @PathVariable int idOrden) {
        preparationOrdersUseCase.addOrderToPreparation(idComerciante, idOrden);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idComerciante}/ordenes/preparacion")
    public ResponseEntity<?> orenesPreparacion(@PathVariable int idComerciante) {
        return ResponseEntity.ok(preparationOrdersUseCase.getPreparationOrders(idComerciante));
    }

    @GetMapping("/{idComerciante}/ordenes/preparacion/{idOrden}")
    public ResponseEntity<?> ordenPreparacion(@PathVariable int idComerciante, @PathVariable int idOrden) {
        return ResponseEntity.ok(preparationOrdersUseCase.getOrderFromPreparation(idComerciante, idOrden));
    }

    @PostMapping("/{id}/completarRegistro")
    public ResponseEntity<?> completarRegistro(@PathVariable int id, @RequestParam("nit") String nit, @RequestParam("camaraComercio") MultipartFile camaraComercio, @RequestParam("rut") MultipartFile rut) {
        try {
            return ResponseEntity.ok(comercianteUseCase.completeRegistration(id, nit, camaraComercio.getBytes(), camaraComercio.getOriginalFilename(), rut.getBytes(), rut.getOriginalFilename()));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error al leer los archivos");
        }
    }
}
