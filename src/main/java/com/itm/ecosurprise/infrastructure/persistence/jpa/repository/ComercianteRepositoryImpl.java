package com.itm.ecosurprise.infrastructure.persistence.jpa.repository;

import com.itm.ecosurprise.domain.model.Comerciante;
import com.itm.ecosurprise.domain.port.out.ComercianteRepository;
import com.itm.ecosurprise.infrastructure.persistence.jpa.entity.ComercianteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ComercianteRepositoryImpl implements ComercianteRepository {

    private final ComercianteJpaRepository comercianteJpaRepository;
    private final ModelMapper modelMapper;

    public ComercianteRepositoryImpl(ComercianteJpaRepository comercianteJpaRepository, ModelMapper modelMapper) {
        this.comercianteJpaRepository = comercianteJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Comerciante save(Comerciante domain) {
        ComercianteEntity entity = modelMapper.map(domain, ComercianteEntity.class);
        ComercianteEntity savedEntity = comercianteJpaRepository.save(entity);
        return modelMapper.map(savedEntity, Comerciante.class);
    }

    @Override
    public Optional<Comerciante> findById(Integer id) {
        return comercianteJpaRepository.findById(id).map(entity -> modelMapper.map(entity, Comerciante.class));
    }

    @Override
    public List<Comerciante> findAll() {
        return comercianteJpaRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, Comerciante.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        comercianteJpaRepository.deleteById(id);
    }
}
