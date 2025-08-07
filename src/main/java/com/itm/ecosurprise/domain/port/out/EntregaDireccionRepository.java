package com.itm.ecosurprise.domain.port.out;

import com.itm.ecosurprise.domain.model.EntregaDireccion;

import java.util.Optional;
import java.util.List;

public interface EntregaDireccionRepository {
    EntregaDireccion save(EntregaDireccion entregaDireccion);
    Optional<EntregaDireccion> findById(Integer id);
    List<EntregaDireccion> findAll();
    void deleteById(Integer id);
}
