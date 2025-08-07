package com.itm.ecosurprise.domain.port.out;

import com.itm.ecosurprise.domain.model.UsuarioDireccion;

import java.util.Optional;
import java.util.List;

public interface UsuarioDireccionRepository {
    UsuarioDireccion save(UsuarioDireccion usuarioDireccion);
    Optional<UsuarioDireccion> findById(Integer id);
    List<UsuarioDireccion> findAll();
    void deleteById(Integer id);
}
