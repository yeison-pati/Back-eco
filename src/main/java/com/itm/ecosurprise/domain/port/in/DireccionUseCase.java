package com.itm.ecosurprise.domain.port.in;

import com.itm.ecosurprise.domain.model.Direccion;

import java.util.List;
import java.util.Optional;

public interface DireccionUseCase {
    List<Direccion> getAll();
    Optional<Direccion> getById(int id);
    Direccion create(Direccion direccion);
    Direccion update(Direccion direccion);
    void deleteById(int id);
}
