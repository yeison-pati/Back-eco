package com.itm.ecosurprise.domain.port.in;

import com.itm.ecosurprise.domain.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductUseCase {
    List<Producto> getAllProducts();
    Optional<Producto> getProductById(int id);
    Producto createProduct(int idComerciante, Producto producto, byte[] imagen, String fileName);
    Producto updateProduct(int idComerciante, int idProducto, Producto producto, byte[] imagen, String fileName);
    void deleteProduct(int id);
}
