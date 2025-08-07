package com.itm.ecosurprise.domain.port.out;

import com.itm.ecosurprise.domain.model.Orden;

import java.util.Optional;
import java.util.List;

public interface OrdenRepository {
    Orden save(Orden orden);
    Optional<Orden> findById(Integer id);
    List<Orden> findAll();
    void deleteById(Integer id);
    List<Orden> findAllByIdComerciante(int idComerciante);
    Optional<Orden> findByIdAndComerciante(int idOrden, int idComerciante);
}
