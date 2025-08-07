package com.itm.ecosurprise.domain.port.in;

import com.itm.ecosurprise.domain.model.Fecha;

import java.util.List;
import java.util.Optional;

public interface FechaUseCase {
    List<Fecha> getAll();
    Optional<Fecha> getById(int id);
    Fecha create(Fecha fecha);
    Fecha update(Fecha fecha);
    void deleteById(int id);
}
