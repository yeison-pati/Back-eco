package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.Direccion;
import com.itm.ecosurprise.domain.port.in.DireccionUseCase;
import com.itm.ecosurprise.domain.port.out.DireccionRepository;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

//auto bean y auto inyeccion de dependencias con service
@Service
//es para crear el constructor con todos los atributos
@AllArgsConstructor
public class DireccionApplicationService implements DireccionUseCase {

    private final DireccionRepository direccionRepository;

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
