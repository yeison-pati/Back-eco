package com.itm.ecosurprise.infrastructure.persistence.jpa.repository;

import com.itm.ecosurprise.domain.model.Telefono;
import com.itm.ecosurprise.domain.port.out.TelefonoRepository;
import com.itm.ecosurprise.infrastructure.persistence.jpa.entity.TelefonoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TelefonoRepositoryImpl implements TelefonoRepository {

    private final TelefonoJpaRepository telefonoJpaRepository;
    private final ModelMapper modelMapper;

    public TelefonoRepositoryImpl(TelefonoJpaRepository telefonoJpaRepository, ModelMapper modelMapper) {
        this.telefonoJpaRepository = telefonoJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Telefono save(Telefono domain) {
        TelefonoEntity entity = modelMapper.map(domain, TelefonoEntity.class);
        TelefonoEntity savedEntity = telefonoJpaRepository.save(entity);
        return modelMapper.map(savedEntity, Telefono.class);
    }

    @Override
    public Optional<Telefono> findById(Integer id) {
        return telefonoJpaRepository.findById(id).map(entity -> modelMapper.map(entity, Telefono.class));
    }

    @Override
    public List<Telefono> findAll() {
        return telefonoJpaRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, Telefono.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        telefonoJpaRepository.deleteById(id);
    }
}
