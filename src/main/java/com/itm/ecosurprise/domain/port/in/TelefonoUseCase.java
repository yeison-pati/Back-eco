package com.itm.ecosurprise.domain.port.in;

import com.itm.ecosurprise.domain.model.Telefono;

import java.util.List;
import java.util.Optional;

public interface TelefonoUseCase {
    List<Telefono> getAll();
    Optional<Telefono> getById(int id);
    Telefono create(Telefono telefono);
    Telefono update(Telefono telefono);
    void deleteById(int id);
}
