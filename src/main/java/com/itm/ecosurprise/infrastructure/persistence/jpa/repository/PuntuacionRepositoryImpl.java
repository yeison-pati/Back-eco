package com.itm.ecosurprise.infrastructure.persistence.jpa.repository;

import com.itm.ecosurprise.domain.model.Puntuacion;
import com.itm.ecosurprise.domain.port.out.PuntuacionRepository;
import com.itm.ecosurprise.infrastructure.persistence.jpa.entity.PuntuacionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PuntuacionRepositoryImpl implements PuntuacionRepository {

    private final PuntuacionJpaRepository puntuacionJpaRepository;
    private final ModelMapper modelMapper;

    public PuntuacionRepositoryImpl(PuntuacionJpaRepository puntuacionJpaRepository, ModelMapper modelMapper) {
        this.puntuacionJpaRepository = puntuacionJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Puntuacion save(Puntuacion domain) {
        PuntuacionEntity entity = modelMapper.map(domain, PuntuacionEntity.class);
        PuntuacionEntity savedEntity = puntuacionJpaRepository.save(entity);
        return modelMapper.map(savedEntity, Puntuacion.class);
    }

    @Override
    public Optional<Puntuacion> findById(Integer id) {
        return puntuacionJpaRepository.findById(id).map(entity -> modelMapper.map(entity, Puntuacion.class));
    }

    @Override
    public List<Puntuacion> findAll() {
        return puntuacionJpaRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, Puntuacion.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        puntuacionJpaRepository.deleteById(id);
    }
}
