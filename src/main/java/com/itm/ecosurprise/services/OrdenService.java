package com.itm.ecosurprise.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itm.ecosurprise.classes.EstadoOrdenFactory;
import com.itm.ecosurprise.dtos.CarritoDTO;
import com.itm.ecosurprise.dtos.ProductoDTO;
import com.itm.ecosurprise.enums.EstadoOrden;
import com.itm.ecosurprise.interfaces.EstadoOrdenState;
import com.itm.ecosurprise.models.Consumidor;
import com.itm.ecosurprise.models.Direccion;
import com.itm.ecosurprise.models.Fecha;
import com.itm.ecosurprise.models.Orden;
import com.itm.ecosurprise.models.OrdenProducto;
import com.itm.ecosurprise.models.Pago;
import com.itm.ecosurprise.models.Producto;
import com.itm.ecosurprise.models.UsuarioDireccion;
import com.itm.ecosurprise.repositories.IComerciante;
import com.itm.ecosurprise.repositories.IConsumidor;
import com.itm.ecosurprise.repositories.IOrden;
import com.itm.ecosurprise.repositories.IProducto;

@Service
public class OrdenService {

    @Autowired
    private IOrden ordenRepository;
    @Autowired
    private IConsumidor consumidorRepository;
    @Autowired
    private IComerciante comercianteRepository;
    @Autowired
    private FechaService fechaService;
    @Autowired
    private CarritoService carritoService;
    @Autowired
    private PagoService pagoService;
    @Autowired
    private IProducto productoRepository;
    @Autowired
    private OrdenProductoService ordenProductoService;

    public ResponseEntity<?> obtenerPorId(int idComerciante, int idOrden) {
        try {
            if (!ordenRepository.existsById(idComerciante)) {
                throw new RuntimeException("Comerciante no encontrado");
            }
            return ResponseEntity.ok(obtenerXID(idOrden));
        } catch (Exception e) {
            // NOT_FOUND es correcto para recursos no encontrados
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerTodos(int idComerciante) {
        try {
            comercianteRepository.findById(idComerciante)
                    .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));
            return ResponseEntity.ok(ordenRepository.findAll());
        } catch (Exception e) {
            if (e.getMessage().contains("no encontrado")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            // INTERNAL_SERVER_ERROR para otros errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public Orden obtenerXID(int idOrden) {
        return ordenRepository.findById(idOrden)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con ID: " + idOrden));
    }

    public ResponseEntity<?> crear(int idConsumidor, Orden orden) {
        try {
            // Obtener carrito del consumidor
            CarritoDTO carrito = carritoService.obtenerCarrito(idConsumidor);
            if (carrito.getProductos().isEmpty()) {
                // BAD_REQUEST es correcto para datos de entrada inválidos
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El carrito está vacío. No se pueden agregar productos a la orden.");
            }

            // Validar stock de los productos
            for (ProductoDTO productoDTO : carrito.getProductos()) {
                Producto producto = productoRepository.findById(productoDTO.getId())
                        .orElseThrow(
                                () -> new RuntimeException("Producto no encontrado con ID: " + productoDTO.getId()));

                if (producto.getStock() < productoDTO.getCantidad()) {
                    // BAD_REQUEST es correcto para datos de entrada inválidos
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("No hay suficiente stock para el producto: " + producto.getNombre());
                }
            }

            // Obtener el consumidor
            Consumidor consumidor = consumidorRepository.findById(idConsumidor)
                    .orElseThrow(() -> new RuntimeException("Consumidor no encontrado con ID: " + idConsumidor));
            orden.setConsumidor(consumidor);

            // Crear y asignar fecha de la orden
            Fecha fecha = fechaService.crear(orden.getFechaOrden());
            orden.setFechaOrden(fecha);

            // Calcular monto total
            int monto = carritoService.calcularTotal(idConsumidor);
            orden.setMontoTotal(monto);

            // Asignar estado inicial a la orden
            orden.setEstadoOrden(EstadoOrden.pendiente.name());

            // Asignar dirección de entrega
            Direccion direccion = consumidor.getDirecciones().stream()
                    .map(UsuarioDireccion::getDireccion)
                    .filter(d -> d.getIdDireccion() == orden.getDireccionEntrega().getIdDireccion())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(
                            "Dirección no encontrada con ID: " + orden.getDireccionEntrega().getIdDireccion()));
            orden.setDireccionEntrega(direccion);

            // Guardar la orden y crear el pago
            Orden ordenExistente = ordenRepository.save(orden);
            ordenExistente = obtenerXID(ordenExistente.getIdOrden());

            Pago nuevoPago = new Pago();
            nuevoPago.setMetodoPago(orden.getPago().getMetodoPago());
            nuevoPago.setEstadoPago(orden.getPago().getEstadoPago());
            nuevoPago.setFechaPago(fecha);
            nuevoPago.setMontoPagado(monto);
            nuevoPago.setOrden(ordenExistente);
            ordenExistente.setPago(pagoService.crear(nuevoPago));

            // Guardar la orden con el pago asignado
            ordenExistente = ordenRepository.save(ordenExistente);

            // Asociar los productos con la orden y actualizar stock
            List<OrdenProducto> productos = new ArrayList<>();
            for (ProductoDTO productoDTO : carrito.getProductos()) {
                Producto producto = productoRepository.findById(productoDTO.getId())
                        .orElseThrow(() -> new RuntimeException(
                                "Producto no encontrado con ID: " + productoDTO.getId()));

                // Actualizar stock
                producto.setStock(producto.getStock() - productoDTO.getCantidad());
                productoRepository.save(producto);

                // Crear asociación orden-producto
                OrdenProducto ordenProducto = new OrdenProducto();
                ordenProducto.setOrden(ordenExistente);
                ordenProducto.setProducto(producto);
                productos.add(ordenProductoService.crear(ordenProducto));
            }
            ordenExistente.setProductos(productos);

            // Vaciar carrito después de procesar la orden
            carritoService.limpiarCarrito(idConsumidor);

            // CREATED es más apropiado para recursos creados
            return ResponseEntity.status(HttpStatus.CREATED).body(ordenRepository.save(ordenExistente));
        } catch (Exception e) {
            // Loguear el error para diagnóstico
            e.printStackTrace();

            // Devolver respuesta de error con mensaje específico
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            error.put("causa", e.getCause() != null ? e.getCause().getMessage() : "Desconocida");

            // Diferenciar entre tipos de errores
            if (e.getMessage().contains("no encontrado")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    public Orden actualizar(Orden idOrden) {
        return ordenRepository.save(idOrden);
    }

    public String eliminarOrden(int idOrden) {
        ordenRepository.deleteById(idOrden);
        return "Orden eliminada con exito";
    }

    public Orden confirmar(int idOrden) {
        Orden orden = ordenRepository.findById(idOrden)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        EstadoOrdenState estadoActual = EstadoOrdenFactory.getEstado(orden);
        estadoActual.confirmar(orden); // Delegar la lógica de confirmación al estado actual

        return ordenRepository.save(orden); // Guardar el cambio de estado
    }

    public ResponseEntity<?> cancelar(int idConsumidor, int idOrden) {
        try {
            Orden ordenExistente = ordenRepository.findById(idOrden)
                    .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

            EstadoOrdenState estadoActual = EstadoOrdenFactory.getEstado(ordenExistente);
            estadoActual.cancelar(ordenExistente); // Delegar la lógica de confirmación al estado actual
            ordenRepository.save(ordenExistente); // Guardar el cambio de estado

            // OK es correcto para operaciones exitosas con información
            return ResponseEntity.ok("Orden cancelada con exito");
        } catch (Exception e) {
            if (e.getMessage().contains("no encontrada")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            // INTERNAL_SERVER_ERROR para otros errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerTodosPorComerciante(int idComerciante) {
        try {
            comercianteRepository.findById(idComerciante)
                    .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));

            List<Orden> ordenes = ordenRepository.findAllByIdComerciante(idComerciante);

            // Para listas vacías, es mejor retornar 200 con lista vacía en lugar de 404
            return ResponseEntity.ok(ordenes);
        } catch (Exception e) {
            if (e.getMessage().contains("no encontrado")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            // INTERNAL_SERVER_ERROR para otros errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> productosPorIdAndComerciante(int idComerciante, int idOrden) {
        try {
            comercianteRepository.findById(idComerciante)
                    .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));

            Orden orden = ordenRepository.findByIdAndComerciante(idOrden, idComerciante);

            List<OrdenProducto> productosFiltrados = orden.getProductos().stream()
                    .filter(o -> o.getProducto().getComerciante().getIdUsuario() == idComerciante)
                    .collect(Collectors.toList());

            if (productosFiltrados.isEmpty()) {
                // NOT_FOUND es apropiado cuando no se encuentran recursos específicos
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Orden no encontrada o no tiene productos del comerciante");
            }

            return ResponseEntity.ok(productosFiltrados);
        } catch (Exception e) {
            if (e.getMessage() != null && (e.getMessage().contains("no encontrado") ||
                    e.getMessage().contains("no encontrada"))) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            // INTERNAL_SERVER_ERROR para otros errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}