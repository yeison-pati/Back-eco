package com.itm.ecosurprise.infrastructure.persistence.jpa.repository;

import com.itm.ecosurprise.domain.model.Entrega;
import com.itm.ecosurprise.domain.port.out.EntregaRepository;
import com.itm.ecosurprise.infrastructure.persistence.jpa.entity.EntregaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EntregaRepositoryImpl implements EntregaRepository {

    private final EntregaJpaRepository entregaJpaRepository;
    private final ModelMapper modelMapper;

    public EntregaRepositoryImpl(EntregaJpaRepository entregaJpaRepository, ModelMapper modelMapper) {
        this.entregaJpaRepository = entregaJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Entrega save(Entrega domain) {
        EntregaEntity entity = modelMapper.map(domain, EntregaEntity.class);
        EntregaEntity savedEntity = entregaJpaRepository.save(entity);
        return modelMapper.map(savedEntity, Entrega.class);
    }

    @Override
    public Optional<Entrega> findById(Integer id) {
        return entregaJpaRepository.findById(id).map(entity -> modelMapper.map(entity, Entrega.class));
    }

    @Override
    public List<Entrega> findAll() {
        return entregaJpaRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, Entrega.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        entregaJpaRepository.deleteById(id);
    }
}
