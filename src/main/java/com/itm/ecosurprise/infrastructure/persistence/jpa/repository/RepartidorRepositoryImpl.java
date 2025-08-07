package com.itm.ecosurprise.infrastructure.persistence.jpa.repository;

import com.itm.ecosurprise.domain.model.Repartidor;
import com.itm.ecosurprise.domain.port.out.RepartidorRepository;
import com.itm.ecosurprise.infrastructure.persistence.jpa.entity.RepartidorEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RepartidorRepositoryImpl implements RepartidorRepository {

    private final RepartidorJpaRepository repartidorJpaRepository;
    private final ModelMapper modelMapper;

    public RepartidorRepositoryImpl(RepartidorJpaRepository repartidorJpaRepository, ModelMapper modelMapper) {
        this.repartidorJpaRepository = repartidorJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Repartidor save(Repartidor domain) {
        RepartidorEntity entity = modelMapper.map(domain, RepartidorEntity.class);
        RepartidorEntity savedEntity = repartidorJpaRepository.save(entity);
        return modelMapper.map(savedEntity, Repartidor.class);
    }

    @Override
    public Optional<Repartidor> findById(Integer id) {
        return repartidorJpaRepository.findById(id).map(entity -> modelMapper.map(entity, Repartidor.class));
    }

    @Override
    public List<Repartidor> findAll() {
        return repartidorJpaRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, Repartidor.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        repartidorJpaRepository.deleteById(id);
    }
}
