package com.itm.ecosurprise.domain.port.out;

import com.itm.ecosurprise.domain.model.Puntuacion;

import java.util.Optional;
import java.util.List;

public interface PuntuacionRepository {
    Puntuacion save(Puntuacion puntuacion);
    Optional<Puntuacion> findById(Integer id);
    List<Puntuacion> findAll();
    void deleteById(Integer id);
}
