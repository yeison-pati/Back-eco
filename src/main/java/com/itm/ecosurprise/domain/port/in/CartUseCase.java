package com.itm.ecosurprise.domain.port.in;

import com.itm.ecosurprise.domain.model.Cart;

public interface CartUseCase {
    Cart getCart(int idConsumidor);
    void addProduct(int idConsumidor, int idProducto, int cantidad);
    void removeProduct(int idConsumidor, int idProducto);
    void changeProductQuantity(int idConsumidor, int idProducto, int cantidad);
    void clearCart(int idConsumidor);
}
