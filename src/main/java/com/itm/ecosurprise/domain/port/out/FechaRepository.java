package com.itm.ecosurprise.domain.port.out;

import com.itm.ecosurprise.domain.model.Fecha;

import java.util.Optional;
import java.util.List;

public interface FechaRepository {
    Fecha save(Fecha fecha);
    Optional<Fecha> findById(Integer id);
    List<Fecha> findAll();
    void deleteById(Integer id);
}
