package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.EntregaDireccion;
import com.itm.ecosurprise.domain.port.in.EntregaDireccionUseCase;
import com.itm.ecosurprise.domain.port.out.EntregaDireccionRepository;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

//auto bean y auto inyeccion de dependencias con service
@Service
//es para crear el constructor con todos los atributos
@AllArgsConstructor
public class EntregaDireccionApplicationService implements EntregaDireccionUseCase {

    private final EntregaDireccionRepository entregaDireccionRepository;

    @Override
    public List<EntregaDireccion> getAll() {
        return entregaDireccionRepository.findAll();
    }

    @Override
    public Optional<EntregaDireccion> getById(int id) {
        return entregaDireccionRepository.findById(id);
    }

    @Override
    public EntregaDireccion create(EntregaDireccion entregaDireccion) {
        return entregaDireccionRepository.save(entregaDireccion);
    }

    @Override
    public EntregaDireccion update(EntregaDireccion entregaDireccion) {
        return entregaDireccionRepository.save(entregaDireccion);
    }

    @Override
    public void deleteById(int id) {
        entregaDireccionRepository.deleteById(id);
    }
}
