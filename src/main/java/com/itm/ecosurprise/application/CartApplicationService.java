package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.Cart;
import com.itm.ecosurprise.domain.model.CartItem;
import com.itm.ecosurprise.domain.model.Producto;
import com.itm.ecosurprise.domain.port.in.CartUseCase;
import com.itm.ecosurprise.domain.port.out.ConsumidorRepository;
import com.itm.ecosurprise.domain.port.out.ProductoRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;

//auto bean y auto inyeccion de dependencias con service
@Service
//es para crear el constructor con todos los atributos
@AllArgsConstructor
public class CartApplicationService implements CartUseCase {

    private final Map<Integer, Cart> carts = new HashMap<>();
    private final ConsumidorRepository consumidorRepository;
    private final ProductoRepository productoRepository;
    
    @Override
    public Cart getCart(int idConsumidor) {
        consumidorRepository.findById(idConsumidor)
                .orElseThrow(() -> new RuntimeException("Consumidor no encontrado"));

        Cart cart = carts.computeIfAbsent(idConsumidor, id -> new Cart());
        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
        }
        return cart;
    }

    @Override
    public void addProduct(int idConsumidor, int idProducto, int cantidad) {
        Cart cart = getCart(idConsumidor);
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProducto().getIdProducto() == idProducto)
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setCantidad(existingItem.get().getCantidad() + cantidad);
        } else {
            CartItem newItem = new CartItem();
            newItem.setProducto(producto);
            newItem.setCantidad(cantidad);
            cart.getItems().add(newItem);
        }
        updateCartTotal(cart);
    }

    @Override
    public void removeProduct(int idConsumidor, int idProducto) {
        Cart cart = getCart(idConsumidor);
        cart.getItems().removeIf(item -> item.getProducto().getIdProducto() == idProducto);
        updateCartTotal(cart);
    }

    @Override
    public void changeProductQuantity(int idConsumidor, int idProducto, int cantidad) {
        Cart cart = getCart(idConsumidor);
        CartItem item = cart.getItems().stream()
                .filter(i -> i.getProducto().getIdProducto() == idProducto)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en el carrito"));
        item.setCantidad(cantidad);
        updateCartTotal(cart);
    }

    @Override
    public void clearCart(int idConsumidor) {
        carts.remove(idConsumidor);
    }

    private void updateCartTotal(Cart cart) {
        int total = cart.getItems().stream()
                .mapToInt(item -> item.getProducto().getPrecio() * item.getCantidad())
                .sum();
        cart.setTotal(total);
    }
}
