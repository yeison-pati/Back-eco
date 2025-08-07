package com.itm.ecosurprise.domain.port.in;

import com.itm.ecosurprise.domain.model.EntregaDireccion;

import java.util.List;
import java.util.Optional;

public interface EntregaDireccionUseCase {
    List<EntregaDireccion> getAll();
    Optional<EntregaDireccion> getById(int id);
    EntregaDireccion create(EntregaDireccion entregaDireccion);
    EntregaDireccion update(EntregaDireccion entregaDireccion);
    void deleteById(int id);
}
