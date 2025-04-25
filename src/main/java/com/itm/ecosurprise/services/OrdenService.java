package com.itm.ecosurprise.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itm.ecosurprise.dtos.CarritoDTO;
import com.itm.ecosurprise.enums.EstadoOrden;
import com.itm.ecosurprise.models.Consumidor;
import com.itm.ecosurprise.models.Direccion;
import com.itm.ecosurprise.models.Fecha;
import com.itm.ecosurprise.models.Orden;
import com.itm.ecosurprise.models.OrdenProducto;
import com.itm.ecosurprise.models.Pago;
import com.itm.ecosurprise.models.Producto;
import com.itm.ecosurprise.models.UsuarioDireccion;
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
    private FechaService fechaService;
    @Autowired
    private CarritoService carritoService;
    @Autowired
    private PagoService pagoService;
    @Autowired
    private IProducto productoRepository;
    @Autowired
    private OrdenProductoService ordenProductoService;

    public List<Orden> obtenerTodos() {
        return ordenRepository.findAll();
    }

    public Orden obtenerXID(int idOrden) {
        return ordenRepository.findById(idOrden)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con ID: " + idOrden));
    }

    public ResponseEntity<?> crear(int idConsumidor, Orden orden) {
        try {
            Consumidor consumidor = consumidorRepository.findById(idConsumidor)
                    .orElseThrow(() -> new RuntimeException("Consumidor no encontrado con ID: " + idConsumidor));
            orden.setConsumidor(consumidor);

            // crear y asignar fecha (del body)
            Fecha fecha = fechaService.crear(orden.getFechaOrden());
            orden.setFechaOrden(fecha);
            // sumar valor de los productos
            int monto = carritoService.calcularTotal(idConsumidor);
            orden.setMontoTotal(monto);

            // asignar un estado inicial
            orden.setEstadoOrden((EstadoOrden.pendiente).toString());

            /*
             * asignar la direccion trayendo de la bd la direccion segun el id
             * en la peticion (orden que recibi como parametro con una direccion)
             */
            Direccion direccion = consumidor.getDirecciones().stream()
                    .map(UsuarioDireccion::getDireccion)
                    .filter(d -> d.getIdDireccion() == orden.getDireccionEntrega().getIdDireccion())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(
                            "Dirección no encontrada con ID: " + orden.getDireccionEntrega().getIdDireccion()));
            orden.setDireccionEntrega(direccion);

            Orden ordenExistente = obtenerXID(ordenRepository.save(orden).getIdOrden());

            // crear y asignar pago
            Pago nuevoPago = new Pago();
            nuevoPago.setMetodoPago(orden.getPago().getMetodoPago());
            nuevoPago.setEstadoPago(orden.getPago().getEstadoPago());
            nuevoPago.setFechaPago(fecha);
            nuevoPago.setMontoPagado(monto);
            nuevoPago.setOrden(ordenExistente);

            ordenExistente.setPago(pagoService.crear(nuevoPago));

            ordenExistente = ordenRepository.save(ordenExistente);

            // obtener carrito del usuario
            CarritoDTO carrito = carritoService.obtenerCarrito(idConsumidor);
            if (carrito.getProductos().isEmpty()) {
                throw new RuntimeException("El carrito está vacío. No se pueden agregar productos a la orden.");
            }

            // traer productos de la bd, segun el id en el carrito y asignar en la orden de
            // la bd
            List<OrdenProducto> productos = (carrito.getProductos().stream()
                    .map(productoDTO -> {
                        Producto producto = productoRepository.findById(productoDTO.getId())
                                .orElseThrow(() -> new RuntimeException(
                                        "Producto no encontrado con ID: " + productoDTO.getId()));
                        OrdenProducto ordenProducto = new OrdenProducto();
                        ordenProducto.setOrden(obtenerXID(orden.getIdOrden()));
                        ordenProducto.setProducto(producto);
                        return ordenProductoService.crear(ordenProducto);
                    })
                    .toList());
            ordenExistente.setProductos(productos);

            return ResponseEntity.ok(ordenRepository.save(ordenExistente));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public Orden actualizar(Orden idOrden) {
        return ordenRepository.save(idOrden);
    }

    public String eliminarOrden(int idOrden) {
        ordenRepository.deleteById(idOrden);
        return "Orden eliminada con exito";
    }

}
