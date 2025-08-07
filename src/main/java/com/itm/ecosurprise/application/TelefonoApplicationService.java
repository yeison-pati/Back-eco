package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.Telefono;
import com.itm.ecosurprise.domain.port.in.TelefonoUseCase;
import com.itm.ecosurprise.domain.port.out.TelefonoRepository;

import java.util.List;
import java.util.Optional;

public class TelefonoApplicationService implements TelefonoUseCase {

    private final TelefonoRepository telefonoRepository;

    public TelefonoApplicationService(TelefonoRepository telefonoRepository) {
        this.telefonoRepository = telefonoRepository;
    }

    @Override
    public List<Telefono> getAll() {
        return telefonoRepository.findAll();
    }

    @Override
    public Optional<Telefono> getById(int id) {
        return telefonoRepository.findById(id);
    }

    @Override
    public Telefono create(Telefono telefono) {
        return telefonoRepository.save(telefono);
    }

    @Override
    public Telefono update(Telefono telefono) {
        return telefonoRepository.save(telefono);
    }

    @Override
    public void deleteById(int id) {
        telefonoRepository.deleteById(id);
    }
}
