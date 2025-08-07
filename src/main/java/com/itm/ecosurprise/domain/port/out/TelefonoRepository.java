package com.itm.ecosurprise.domain.port.out;

import com.itm.ecosurprise.domain.model.Telefono;

import java.util.Optional;
import java.util.List;

public interface TelefonoRepository {
    Telefono save(Telefono telefono);
    Optional<Telefono> findById(Integer id);
    List<Telefono> findAll();
    void deleteById(Integer id);
}
