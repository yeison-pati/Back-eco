package com.itm.ecosurprise.infrastructure.persistence.jpa.repository;

import com.itm.ecosurprise.domain.model.Fecha;
import com.itm.ecosurprise.domain.port.out.FechaRepository;
import com.itm.ecosurprise.infrastructure.persistence.jpa.entity.FechaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FechaRepositoryImpl implements FechaRepository {

    private final FechaJpaRepository fechaJpaRepository;
    private final ModelMapper modelMapper;

    public FechaRepositoryImpl(FechaJpaRepository fechaJpaRepository, ModelMapper modelMapper) {
        this.fechaJpaRepository = fechaJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Fecha save(Fecha domain) {
        FechaEntity entity = modelMapper.map(domain, FechaEntity.class);
        FechaEntity savedEntity = fechaJpaRepository.save(entity);
        return modelMapper.map(savedEntity, Fecha.class);
    }

    @Override
    public Optional<Fecha> findById(Integer id) {
        return fechaJpaRepository.findById(id).map(entity -> modelMapper.map(entity, Fecha.class));
    }

    @Override
    public List<Fecha> findAll() {
        return fechaJpaRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, Fecha.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        fechaJpaRepository.deleteById(id);
    }
}
