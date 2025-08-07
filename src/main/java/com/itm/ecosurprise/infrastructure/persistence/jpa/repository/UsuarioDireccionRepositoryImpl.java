package com.itm.ecosurprise.infrastructure.persistence.jpa.repository;

import com.itm.ecosurprise.domain.model.UsuarioDireccion;
import com.itm.ecosurprise.domain.port.out.UsuarioDireccionRepository;
import com.itm.ecosurprise.infrastructure.persistence.jpa.entity.UsuarioDireccionEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UsuarioDireccionRepositoryImpl implements UsuarioDireccionRepository {

    private final UsuarioDireccionJpaRepository usuarioDireccionJpaRepository;
    private final ModelMapper modelMapper;

    public UsuarioDireccionRepositoryImpl(UsuarioDireccionJpaRepository usuarioDireccionJpaRepository, ModelMapper modelMapper) {
        this.usuarioDireccionJpaRepository = usuarioDireccionJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UsuarioDireccion save(UsuarioDireccion domain) {
        UsuarioDireccionEntity entity = modelMapper.map(domain, UsuarioDireccionEntity.class);
        UsuarioDireccionEntity savedEntity = usuarioDireccionJpaRepository.save(entity);
        return modelMapper.map(savedEntity, UsuarioDireccion.class);
    }

    @Override
    public Optional<UsuarioDireccion> findById(Integer id) {
        return usuarioDireccionJpaRepository.findById(id).map(entity -> modelMapper.map(entity, UsuarioDireccion.class));
    }

    @Override
    public List<UsuarioDireccion> findAll() {
        return usuarioDireccionJpaRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, UsuarioDireccion.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        usuarioDireccionJpaRepository.deleteById(id);
    }
}
