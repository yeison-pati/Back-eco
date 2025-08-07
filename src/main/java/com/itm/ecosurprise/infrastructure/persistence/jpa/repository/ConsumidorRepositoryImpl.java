package com.itm.ecosurprise.infrastructure.persistence.jpa.repository;

import com.itm.ecosurprise.domain.model.Consumidor;
import com.itm.ecosurprise.domain.port.out.ConsumidorRepository;
import com.itm.ecosurprise.infrastructure.persistence.jpa.entity.ConsumidorEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ConsumidorRepositoryImpl implements ConsumidorRepository {

    private final ConsumidorJpaRepository consumidorJpaRepository;
    private final ModelMapper modelMapper;

    public ConsumidorRepositoryImpl(ConsumidorJpaRepository consumidorJpaRepository, ModelMapper modelMapper) {
        this.consumidorJpaRepository = consumidorJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Consumidor save(Consumidor domain) {
        ConsumidorEntity entity = modelMapper.map(domain, ConsumidorEntity.class);
        ConsumidorEntity savedEntity = consumidorJpaRepository.save(entity);
        return modelMapper.map(savedEntity, Consumidor.class);
    }

    @Override
    public Optional<Consumidor> findById(Integer id) {
        return consumidorJpaRepository.findById(id).map(entity -> modelMapper.map(entity, Consumidor.class));
    }

    @Override
    public List<Consumidor> findAll() {
        return consumidorJpaRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, Consumidor.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        consumidorJpaRepository.deleteById(id);
    }
}
