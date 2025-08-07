package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.Consumidor;
import com.itm.ecosurprise.domain.port.in.ConsumidorUseCase;
import com.itm.ecosurprise.domain.port.out.ConsumidorRepository;

import java.util.List;
import java.util.Optional;

public class ConsumidorApplicationService implements ConsumidorUseCase {

    private final ConsumidorRepository consumidorRepository;

    public ConsumidorApplicationService(ConsumidorRepository consumidorRepository) {
        this.consumidorRepository = consumidorRepository;
    }

    @Override
    public List<Consumidor> getAll() {
        return consumidorRepository.findAll();
    }

    @Override
    public Optional<Consumidor> getById(int id) {
        return consumidorRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        consumidorRepository.deleteById(id);
    }

    @Override
    public Consumidor update(Consumidor consumidor) {
        return consumidorRepository.save(consumidor);
    }
}
