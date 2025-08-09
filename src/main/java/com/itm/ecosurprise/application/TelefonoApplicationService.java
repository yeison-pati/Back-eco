package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.Telefono;
import com.itm.ecosurprise.domain.port.in.TelefonoUseCase;
import com.itm.ecosurprise.domain.port.out.TelefonoRepository;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

//auto bean y auto inyeccion de dependencias con service
@Service
//es para crear el constructor con todos los atributos
@AllArgsConstructor
public class TelefonoApplicationService implements TelefonoUseCase {

    private final TelefonoRepository telefonoRepository;

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
