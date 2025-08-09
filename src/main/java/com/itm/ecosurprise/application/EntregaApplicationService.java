package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.Entrega;
import com.itm.ecosurprise.domain.port.in.EntregaUseCase;
import com.itm.ecosurprise.domain.port.out.EntregaRepository;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

//auto bean y auto inyeccion de dependencias con service
@Service
//es para crear el constructor con todos los atributos
@AllArgsConstructor
public class EntregaApplicationService implements EntregaUseCase {

    private final EntregaRepository entregaRepository;

    @Override
    public List<Entrega> getAll() {
        return entregaRepository.findAll();
    }

    @Override
    public Optional<Entrega> getById(int id) {
        return entregaRepository.findById(id);
    }

    @Override
    public Entrega create(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    @Override
    public Entrega update(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    @Override
    public void deleteById(int id) {
        entregaRepository.deleteById(id);
    }
}
