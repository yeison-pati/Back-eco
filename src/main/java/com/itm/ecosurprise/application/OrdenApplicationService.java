package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.Orden;
import com.itm.ecosurprise.domain.model.OrdenProducto;
import com.itm.ecosurprise.domain.port.in.OrderUseCase;
import com.itm.ecosurprise.domain.port.out.OrdenRepository;
import com.itm.ecosurprise.domain.port.out.ConsumidorRepository;
import com.itm.ecosurprise.domain.port.out.ProductoRepository;
import com.itm.ecosurprise.domain.port.in.CartUseCase;
import com.itm.ecosurprise.domain.model.Cart;
import com.itm.ecosurprise.domain.model.CartItem;
import com.itm.ecosurprise.domain.model.Consumidor;
import com.itm.ecosurprise.domain.model.Producto;
import com.itm.ecosurprise.classes.EstadoOrdenFactory;
import com.itm.ecosurprise.interfaces.EstadoOrdenState;


import java.util.List;
import java.util.Optional;

public class OrdenApplicationService implements OrderUseCase {

    private final OrdenRepository ordenRepository;
    private final ConsumidorRepository consumidorRepository;
    private final ProductoRepository productoRepository;
    private final CartUseCase cartUseCase;

    public OrdenApplicationService(OrdenRepository ordenRepository, ConsumidorRepository consumidorRepository, ProductoRepository productoRepository, CartUseCase cartUseCase) {
        this.ordenRepository = ordenRepository;
        this.consumidorRepository = consumidorRepository;
        this.productoRepository = productoRepository;
        this.cartUseCase = cartUseCase;
    }

    @Override
    public Optional<Orden> getById(int id) {
        return ordenRepository.findById(id);
    }

    @Override
    public List<Orden> getAll() {
        return ordenRepository.findAll();
    }

    @Override
    public Orden create(int idConsumidor, Orden orden) {
        Consumidor consumidor = consumidorRepository.findById(idConsumidor)
                .orElseThrow(() -> new RuntimeException("Consumidor no encontrado"));

        Cart cart = cartUseCase.getCart(idConsumidor);
        if(cart.getItems().isEmpty()){
            throw new RuntimeException("El carrito está vacío");
        }

        for(CartItem item : cart.getItems()){
            Producto producto = productoRepository.findById(item.getProducto().getIdProducto()).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            if(producto.getStock() < item.getCantidad()){
                throw new RuntimeException("No hay suficiente stock para el producto: " + producto.getNombre());
            }
        }

        orden.setConsumidor(consumidor);
        orden.setMontoTotal(cart.getTotal());

        Orden savedOrden = ordenRepository.save(orden);

        for(CartItem item : cart.getItems()){
            Producto producto = productoRepository.findById(item.getProducto().getIdProducto()).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            producto.setStock(producto.getStock() - item.getCantidad());
            productoRepository.save(producto);
        }

        cartUseCase.clearCart(idConsumidor);

        return savedOrden;
    }

    @Override
    public Orden update(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public void deleteById(int id) {
        ordenRepository.deleteById(id);
    }

    @Override
    public Orden confirm(int id) {
        Orden orden = ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        EstadoOrdenState estadoActual = EstadoOrdenFactory.getEstado(orden);
        estadoActual.confirmar(orden);

        return ordenRepository.save(orden);
    }

    @Override
    public Orden cancel(int id) {
        Orden orden = ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        EstadoOrdenState estadoActual = EstadoOrdenFactory.getEstado(orden);
        estadoActual.cancelar(orden);

        return ordenRepository.save(orden);
    }

    @Override
    public List<Orden> getAllByComerciante(int idComerciante) {
        return ordenRepository.findAllByIdComerciante(idComerciante);
    }

    @Override
    public List<OrdenProducto> getProductsByOrderAndComerciante(int idComerciante, int idOrden) {
        // This logic is a bit more complex, I will implement it later
        return null;
    }
}
