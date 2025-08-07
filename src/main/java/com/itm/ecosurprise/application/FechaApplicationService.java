package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.Fecha;
import com.itm.ecosurprise.domain.port.in.FechaUseCase;
import com.itm.ecosurprise.domain.port.out.FechaRepository;

import java.util.List;
import java.util.Optional;

public class FechaApplicationService implements FechaUseCase {

    private final FechaRepository fechaRepository;

    public FechaApplicationService(FechaRepository fechaRepository) {
        this.fechaRepository = fechaRepository;
    }

    @Override
    public List<Fecha> getAll() {
        return fechaRepository.findAll();
    }

    @Override
    public Optional<Fecha> getById(int id) {
        return fechaRepository.findById(id);
    }

    @Override
    public Fecha create(Fecha fecha) {
        return fechaRepository.save(fecha);
    }

    @Override
    public Fecha update(Fecha fecha) {
        return fechaRepository.save(fecha);
    }

    @Override
    public void deleteById(int id) {
        fechaRepository.deleteById(id);
    }
}
