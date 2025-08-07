package com.itm.ecosurprise.infrastructure.persistence.jpa.repository;

import com.itm.ecosurprise.domain.model.EntregaDireccion;
import com.itm.ecosurprise.domain.port.out.EntregaDireccionRepository;
import com.itm.ecosurprise.infrastructure.persistence.jpa.entity.EntregaDireccionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EntregaDireccionRepositoryImpl implements EntregaDireccionRepository {

    private final EntregaDireccionJpaRepository entregaDireccionJpaRepository;
    private final ModelMapper modelMapper;

    public EntregaDireccionRepositoryImpl(EntregaDireccionJpaRepository entregaDireccionJpaRepository, ModelMapper modelMapper) {
        this.entregaDireccionJpaRepository = entregaDireccionJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EntregaDireccion save(EntregaDireccion domain) {
        EntregaDireccionEntity entity = modelMapper.map(domain, EntregaDireccionEntity.class);
        EntregaDireccionEntity savedEntity = entregaDireccionJpaRepository.save(entity);
        return modelMapper.map(savedEntity, EntregaDireccion.class);
    }

    @Override
    public Optional<EntregaDireccion> findById(Integer id) {
        return entregaDireccionJpaRepository.findById(id).map(entity -> modelMapper.map(entity, EntregaDireccion.class));
    }

    @Override
    public List<EntregaDireccion> findAll() {
        return entregaDireccionJpaRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, EntregaDireccion.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        entregaDireccionJpaRepository.deleteById(id);
    }
}
