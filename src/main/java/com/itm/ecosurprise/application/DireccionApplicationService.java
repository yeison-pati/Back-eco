package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.Direccion;
import com.itm.ecosurprise.domain.port.in.DireccionUseCase;
import com.itm.ecosurprise.domain.port.out.DireccionRepository;

import java.util.List;
import java.util.Optional;

public class DireccionApplicationService implements DireccionUseCase {

    private final DireccionRepository direccionRepository;

    public DireccionApplicationService(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    @Override
    public List<Direccion> getAll() {
        return direccionRepository.findAll();
    }

    @Override
    public Optional<Direccion> getById(int id) {
        return direccionRepository.findById(id);
    }

    @Override
    public Direccion create(Direccion direccion) {
        return direccionRepository.save(direccion);
    }

    @Override
    public Direccion update(Direccion direccion) {
        return direccionRepository.save(direccion);
    }

    @Override
    public void deleteById(int id) {
        direccionRepository.deleteById(id);
    }
}
