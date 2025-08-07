package com.itm.ecosurprise.domain.port.out;

import com.itm.ecosurprise.domain.model.Direccion;

import java.util.Optional;
import java.util.List;

public interface DireccionRepository {
    Direccion save(Direccion direccion);
    Optional<Direccion> findById(Integer id);
    List<Direccion> findAll();
    void deleteById(Integer id);
}
