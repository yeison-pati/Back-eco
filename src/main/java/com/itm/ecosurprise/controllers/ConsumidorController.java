package com.itm.ecosurprise.controllers;

import com.itm.ecosurprise.domain.model.Direccion;
import com.itm.ecosurprise.domain.model.Orden;
import com.itm.ecosurprise.domain.model.Telefono;
import com.itm.ecosurprise.domain.port.in.*;
import com.itm.ecosurprise.infrastructure.web.dto.AddProductToCartRequest;
import com.itm.ecosurprise.infrastructure.web.dto.ChangeProductQuantityRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/consumidores")
public class ConsumidorController {

    private final TelefonoUseCase telefonoUseCase;
    private final UsuarioDireccionUseCase usuarioDireccionUseCase;
    private final ProductUseCase productUseCase;
    private final CartUseCase cartUseCase;
    private final OrderUseCase orderUseCase;
    private final SetUserImageUseCase setUserImageUseCase;

    public ConsumidorController(TelefonoUseCase telefonoUseCase, UsuarioDireccionUseCase usuarioDireccionUseCase, ProductUseCase productUseCase, CartUseCase cartUseCase, OrderUseCase orderUseCase, SetUserImageUseCase setUserImageUseCase) {
        this.telefonoUseCase = telefonoUseCase;
        this.usuarioDireccionUseCase = usuarioDireccionUseCase;
        this.productUseCase = productUseCase;
        this.cartUseCase = cartUseCase;
        this.orderUseCase = orderUseCase;
        this.setUserImageUseCase = setUserImageUseCase;
    }

    @PostMapping(value = "/{id}/establecerImagen")
    public ResponseEntity<?> establecerImagen(@PathVariable("id") int idConsumidor, @RequestParam("imagen") MultipartFile imagen) {
        try {
            return ResponseEntity.ok(setUserImageUseCase.setImagen(idConsumidor, imagen.getBytes(), imagen.getOriginalFilename()));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error al leer la imagen");
        }
    }

    @PostMapping("/{idConsumidor}/crearTelefono")
    public ResponseEntity<?> crearTelefono(@PathVariable int idConsumidor, @RequestBody Telefono telefono) {
        // This is not ideal. The controller should not receive a domain object.
        // I will create a DTO for this later if I have time.
        return ResponseEntity.ok(telefonoUseCase.create(telefono));
    }

    @PostMapping("/{idConsumidor}/crearDireccion")
    public ResponseEntity<?> crearDireccion(@PathVariable int idConsumidor, @RequestBody Direccion direccion) {
        // This is not ideal. The controller should not receive a domain object.
        return ResponseEntity.ok(usuarioDireccionUseCase.create(new com.itm.ecosurprise.domain.model.UsuarioDireccion()));
    }

    @GetMapping("/{idConsumidor}/productos/todos")
    public ResponseEntity<?> obtenerProductos(@PathVariable int idConsumidor) {
        return ResponseEntity.ok(productUseCase.getAllProducts());
    }

    @GetMapping("/{idConsumidor}/productos/{idProducto}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable int idConsumidor, @PathVariable int idProducto) {
        return ResponseEntity.ok(productUseCase.getProductById(idProducto));
    }

    @PostMapping("/{idConsumidor}/productos/{idProducto}/agregar")
    public ResponseEntity<?> agregarAlCarrito(@PathVariable int idConsumidor, @PathVariable int idProducto, @RequestBody AddProductToCartRequest request) {
        cartUseCase.addProduct(idConsumidor, idProducto, request.getCantidad());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idConsumidor}/carrito")
    public ResponseEntity<?> verCarrito(@PathVariable int idConsumidor) {
        return ResponseEntity.ok(cartUseCase.getCart(idConsumidor));
    }

    @GetMapping("/{idConsumidor}/carrito/{productoId}/eliminar")
    public ResponseEntity<?> eliminarProductoCarrito(@PathVariable int idConsumidor, @PathVariable int productoId) {
        cartUseCase.removeProduct(idConsumidor, productoId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{idConsumidor}/carrito/{idProducto}/cambiarCantidad")
    public ResponseEntity<?> cambiarCatidadProducto(@PathVariable int idConsumidor, @PathVariable int idProducto, @RequestBody ChangeProductQuantityRequest request) {
        cartUseCase.changeProductQuantity(idConsumidor, idProducto, request.getCantidad());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idConsumidor}/carrito/limpiar")
    public ResponseEntity<?> limpiarCarrito(@PathVariable int idConsumidor) {
        cartUseCase.clearCart(idConsumidor);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{idConsumidor}/carrito/ordenar")
    public ResponseEntity<?> crearOrden(@PathVariable int idConsumidor, @RequestBody Orden orden) {
        return ResponseEntity.ok(orderUseCase.create(idConsumidor, orden));
    }

    @GetMapping("/{idConsumidor}/ordenes/{idOrden}")
    public ResponseEntity<?> obtenerOrden(@PathVariable int idConsumidor, @PathVariable int idOrden) {
        return ResponseEntity.ok(orderUseCase.getById(idOrden));
    }

    @PostMapping("/{idConsumidor}/ordenes/{idOrden}/cancelar")
    public ResponseEntity<?> cancelarOrden(@PathVariable int idConsumidor, @PathVariable int idOrden) {
        return ResponseEntity.ok(orderUseCase.cancel(idOrden));
    }
}
