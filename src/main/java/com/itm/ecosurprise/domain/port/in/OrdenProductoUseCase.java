package com.itm.ecosurprise.domain.port.in;

import com.itm.ecosurprise.domain.model.OrdenProducto;

import java.util.List;
import java.util.Optional;

public interface OrdenProductoUseCase {
    List<OrdenProducto> getAll();
    Optional<OrdenProducto> getById(int id);
    OrdenProducto create(OrdenProducto ordenProducto);
    OrdenProducto update(OrdenProducto ordenProducto);
    void deleteById(int id);
}
