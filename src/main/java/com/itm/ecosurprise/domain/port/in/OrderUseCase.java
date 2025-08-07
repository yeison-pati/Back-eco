package com.itm.ecosurprise.domain.port.in;

import com.itm.ecosurprise.domain.model.Orden;
import com.itm.ecosurprise.domain.model.OrdenProducto;
import java.util.List;
import java.util.Optional;

public interface OrderUseCase {
    Optional<Orden> getById(int id);
    List<Orden> getAll();
    Orden create(int idConsumidor, Orden orden);
    Orden update(Orden orden);
    void deleteById(int id);
    Orden confirm(int id);
    Orden cancel(int id);
    List<Orden> getAllByComerciante(int idComerciante);
    List<OrdenProducto> getProductsByOrderAndComerciante(int idComerciante, int idOrden);
}
