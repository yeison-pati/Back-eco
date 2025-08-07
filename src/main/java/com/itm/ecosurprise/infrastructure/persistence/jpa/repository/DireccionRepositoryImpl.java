package com.itm.ecosurprise.infrastructure.persistence.jpa.repository;

import com.itm.ecosurprise.domain.model.Direccion;
import com.itm.ecosurprise.domain.port.out.DireccionRepository;
import com.itm.ecosurprise.infrastructure.persistence.jpa.entity.DireccionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DireccionRepositoryImpl implements DireccionRepository {

    private final DireccionJpaRepository direccionJpaRepository;
    private final ModelMapper modelMapper;

    public DireccionRepositoryImpl(DireccionJpaRepository direccionJpaRepository, ModelMapper modelMapper) {
        this.direccionJpaRepository = direccionJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Direccion save(Direccion domain) {
        DireccionEntity entity = modelMapper.map(domain, DireccionEntity.class);
        DireccionEntity savedEntity = direccionJpaRepository.save(entity);
        return modelMapper.map(savedEntity, Direccion.class);
    }

    @Override
    public Optional<Direccion> findById(Integer id) {
        return direccionJpaRepository.findById(id).map(entity -> modelMapper.map(entity, Direccion.class));
    }

    @Override
    public List<Direccion> findAll() {
        return direccionJpaRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, Direccion.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        direccionJpaRepository.deleteById(id);
    }
}
