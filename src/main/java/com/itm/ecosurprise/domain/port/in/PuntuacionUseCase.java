package com.itm.ecosurprise.domain.port.in;

import com.itm.ecosurprise.domain.model.Puntuacion;

import java.util.List;
import java.util.Optional;

public interface PuntuacionUseCase {
    List<Puntuacion> getAll();
    Optional<Puntuacion> getById(int id);
    Puntuacion create(Puntuacion puntuacion);
    Puntuacion update(Puntuacion puntuacion);
    void deleteById(int id);
}
