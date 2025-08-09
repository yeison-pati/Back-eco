package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.Repartidor;
import com.itm.ecosurprise.domain.port.in.RepartidorUseCase;
import com.itm.ecosurprise.domain.port.out.RepartidorRepository;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

//auto bean y auto inyeccion de dependencias con service
@Service
//es para crear el constructor con todos los atributos
@AllArgsConstructor
public class RepartidorApplicationService implements RepartidorUseCase {

    private final RepartidorRepository repartidorRepository;
    
    @Override
    public List<Repartidor> getAll() {
        return repartidorRepository.findAll();
    }

    @Override
    public Optional<Repartidor> getById(int id) {
        return repartidorRepository.findById(id);
    }

    @Override
    public Repartidor create(Repartidor repartidor) {
        return repartidorRepository.save(repartidor);
    }

    @Override
    public Repartidor update(Repartidor repartidor) {
        return repartidorRepository.save(repartidor);
    }

    @Override
    public void deleteById(int id) {
        repartidorRepository.deleteById(id);
    }
}
