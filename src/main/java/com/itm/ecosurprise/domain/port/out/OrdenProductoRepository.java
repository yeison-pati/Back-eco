package com.itm.ecosurprise.domain.port.out;

import com.itm.ecosurprise.domain.model.OrdenProducto;

import java.util.Optional;
import java.util.List;

public interface OrdenProductoRepository {
    OrdenProducto save(OrdenProducto ordenProducto);
    Optional<OrdenProducto> findById(Integer id);
    List<OrdenProducto> findAll();
    void deleteById(Integer id);
}
