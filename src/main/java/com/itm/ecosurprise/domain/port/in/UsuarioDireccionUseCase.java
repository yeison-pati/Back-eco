package com.itm.ecosurprise.domain.port.in;

import com.itm.ecosurprise.domain.model.UsuarioDireccion;

import java.util.List;
import java.util.Optional;

public interface UsuarioDireccionUseCase {
    List<UsuarioDireccion> getAll();
    Optional<UsuarioDireccion> getById(int id);
    UsuarioDireccion create(UsuarioDireccion usuarioDireccion);
    UsuarioDireccion update(UsuarioDireccion usuarioDireccion);
    void deleteById(int id);
}
