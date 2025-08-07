package com.itm.ecosurprise.domain.port.out;

import com.itm.ecosurprise.domain.model.Producto;

import java.util.Optional;
import java.util.List;

public interface ProductoRepository {
    Producto save(Producto producto);
    Optional<Producto> findById(Integer id);
    List<Producto> findAll();
    void deleteById(Integer id);
}
