package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.Puntuacion;
import com.itm.ecosurprise.domain.port.in.PuntuacionUseCase;
import com.itm.ecosurprise.domain.port.out.PuntuacionRepository;

import java.util.List;
import java.util.Optional;

public class PuntuacionApplicationService implements PuntuacionUseCase {

    private final PuntuacionRepository puntuacionRepository;

    public PuntuacionApplicationService(PuntuacionRepository puntuacionRepository) {
        this.puntuacionRepository = puntuacionRepository;
    }

    @Override
    public List<Puntuacion> getAll() {
        return puntuacionRepository.findAll();
    }

    @Override
    public Optional<Puntuacion> getById(int id) {
        return puntuacionRepository.findById(id);
    }

    @Override
    public Puntuacion create(Puntuacion puntuacion) {
        return puntuacionRepository.save(puntuacion);
    }

    @Override
    public Puntuacion update(Puntuacion puntuacion) {
        return puntuacionRepository.save(puntuacion);
    }

    @Override
    public void deleteById(int id) {
        puntuacionRepository.deleteById(id);
    }
}
