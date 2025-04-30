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
import com.itm.ecosurprise.repositories.IOrdenProducto;
import com.itm.ecosurprise.repositories.IProducto;

@Service
public class OrdenService {

    // @Autowired
    // private EstadoOrdenFactory estadoOrdenFactory ;
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
    @Autowired
    private IOrdenProducto ordenProductoRepository;

    /**
     * Obtiene una orden específica dada la ID del comerciante y la ID de la orden.
     * 
     * @param idComerciante ID del comerciante asociado.
     * @param idOrden       ID de la orden a obtener.
     * @return ResponseEntity con la orden solicitada o un mensaje de error.
     */
    public ResponseEntity<?> obtenerOrden(int idComerciante, int idOrden) {
        try {
            if (!ordenRepository.existsById(idComerciante)) {
                throw new RuntimeException("Comerciante no encontrado");
            }
            return ResponseEntity.ok(obtenerXID(idOrden));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    /**
     * Obtiene todas las órdenes asociadas a un comerciante específico.
     * 
     * @param idComerciante ID del comerciante.
     * @return ResponseEntity con la lista de órdenes o un mensaje de error.
     */
    public ResponseEntity<?> obtenerTodos(int idComerciante) {
        try {
            comercianteRepository.findById(idComerciante)
                    .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));
            return ResponseEntity.ok(ordenRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    /**
     * Obtiene una orden específica mediante su ID.
     * 
     * @param idOrden ID de la orden.
     * @return La orden encontrada.
     */
    public Orden obtenerXID(int idOrden) {
        return ordenRepository.findById(idOrden)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con ID: " + idOrden));
    }

    /**
     * Crea una nueva orden, valida el carrito de compras del consumidor, asigna
     * fecha,
     * calcula el total, asigna estado de la orden, y guarda la orden junto con los
     * productos.
     * 
     * @param idConsumidor ID del consumidor que realiza la orden.
     * @param orden        Objeto de la orden que contiene los detalles de la
     *                     compra.
     * @return ResponseEntity con la orden creada o un mensaje de error.
     */
    public ResponseEntity<?> crear(int idConsumidor, Orden orden) {
        try {
            // Obtener carrito del consumidor
            CarritoDTO carrito = carritoService.obtenerCarrito(idConsumidor);
            if (carrito.getProductos().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("El carrito está vacío. No se pueden agregar productos a la orden.");
            }

            // Validar stock de los productos
            for (ProductoDTO productoDTO : carrito.getProductos()) {
                Producto producto = productoRepository.findById(productoDTO.getId())
                        .orElseThrow(
                                () -> new RuntimeException("Producto no encontrado con ID: " + productoDTO.getId()));

                if (producto.getStock() < productoDTO.getCantidad()) {
                    return ResponseEntity.badRequest()
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

            return ResponseEntity.ok(ordenRepository.save(ordenExistente));
        } catch (Exception e) {
            // Loguear el error para diagnóstico
            e.printStackTrace();

            // Devolver respuesta de error con mensaje específico
            Map<String, String> error = new HashMap<>();
            error.put("mensaje", e.getMessage());
            error.put("causa", e.getCause() != null ? e.getCause().getMessage() : "Desconocida");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    /**
     * Actualiza una orden existente en la base de datos.
     * 
     * @param idOrden La orden con los datos actualizados.
     * @return La orden actualizada.
     */
    public Orden actualizar(Orden idOrden) {
        return ordenRepository.save(idOrden);
    }

    /**
     * Elimina una orden existente en la base de datos.
     * 
     * @param idOrden ID de la orden a eliminar.
     * @return Mensaje de éxito indicando que la orden fue eliminada.
     */
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

    public ResponseEntity<?> cancelar(int idComerciante, int idOrden) {
        try {
            Orden ordenExistente = ordenRepository.findById(idOrden)
                    .orElseThrow(() -> new RuntimeException("Orden no encontrada con ID: " + idOrden));

            EstadoOrdenState estadoActual = EstadoOrdenFactory.getEstado(ordenExistente);
            estadoActual.cancelar(ordenExistente); // Delegar la lógica de confirmación al estado actual
            ordenRepository.save(ordenExistente); // Guardar el cambio de estado
            comercianteRepository.findById(idComerciante)
                    .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));

            List<OrdenProducto> ordenesProducto = ordenProductoRepository.findAll();

            List<Orden> ordenesDelComerciante = ordenesProducto.stream()
                    .filter(op -> op.getProducto().getComerciante().getIdUsuario() == idComerciante)
                    .map(OrdenProducto::getOrden)
                    .filter(orden -> orden != null && !"cancelada".equalsIgnoreCase(orden.getEstadoOrden()))
                    .distinct()
                    .collect(Collectors.toList());

            return ResponseEntity.ok(ordenesDelComerciante);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerOrdenesComerciante(int idComerciante) {
        try {
            comercianteRepository.findById(idComerciante)
                    .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));

            List<OrdenProducto> ordenesProducto = ordenProductoRepository.findAll();

            // Agrupar productos por orden, pero solo si pertenecen al comerciante
            Map<Orden, List<OrdenProducto>> ordenesAgrupadas = ordenesProducto.stream()
                    .filter(op -> op.getProducto().getComerciante().getIdUsuario() == idComerciante)
                    .filter(op -> op.getOrden() != null
                            && !"cancelada".equalsIgnoreCase(op.getOrden().getEstadoOrden()))
                    .collect(Collectors.groupingBy(OrdenProducto::getOrden));

            // Asignar solo los productos del comerciante a cada orden
            List<Orden> ordenesFiltradas = new ArrayList<>();
            for (Map.Entry<Orden, List<OrdenProducto>> entry : ordenesAgrupadas.entrySet()) {
                Orden orden = entry.getKey();
                orden.setProductos(entry.getValue()); // Asegúrate de tener setOrdenProductos()
                ordenesFiltradas.add(orden);
            }

            return ResponseEntity.ok(ordenesFiltradas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public ResponseEntity<?> obtenerOrdenComerciante(int idComerciante, int idOrden) {
        try {
            comercianteRepository.findById(idComerciante)
                    .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));

            List<OrdenProducto> ordenesProducto = ordenProductoRepository.findAll();

            // Filtrar productos que correspondan al comerciante y a la orden específica
            List<OrdenProducto> productosFiltrados = ordenesProducto.stream()
                    .filter(op -> op.getProducto().getComerciante().getIdUsuario() == idComerciante)
                    .filter(op -> op.getOrden() != null && op.getOrden().getIdOrden() == idOrden)
                    .filter(op -> !"cancelada".equalsIgnoreCase(op.getOrden().getEstadoOrden()))
                    .collect(Collectors.toList());

            if (productosFiltrados.isEmpty()) {
                throw new RuntimeException("Orden no encontrada o no tiene productos del comerciante");
            }

            Orden orden = productosFiltrados.get(0).getOrden(); // Es la misma orden en todos los productos
            orden.setProductos(productosFiltrados); // Asigna solo los productos del comerciante

            return ResponseEntity.ok(orden);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
