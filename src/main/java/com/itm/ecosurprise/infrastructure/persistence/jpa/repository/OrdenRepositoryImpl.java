package com.itm.ecosurprise.infrastructure.persistence.jpa.repository;

import com.itm.ecosurprise.domain.model.Orden;
import com.itm.ecosurprise.domain.port.out.OrdenRepository;
import com.itm.ecosurprise.infrastructure.persistence.jpa.entity.OrdenEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrdenRepositoryImpl implements OrdenRepository {

    private final OrdenJpaRepository ordenJpaRepository;
    private final ModelMapper modelMapper;

    public OrdenRepositoryImpl(OrdenJpaRepository ordenJpaRepository, ModelMapper modelMapper) {
        this.ordenJpaRepository = ordenJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Orden save(Orden orden) {
        OrdenEntity ordenEntity = modelMapper.map(orden, OrdenEntity.class);
        OrdenEntity savedOrden = ordenJpaRepository.save(ordenEntity);
        return modelMapper.map(savedOrden, Orden.class);
    }

    @Override
    public Optional<Orden> findById(Integer id) {
        return ordenJpaRepository.findById(id).map(entity -> modelMapper.map(entity, Orden.class));
    }

    @Override
    public List<Orden> findAll() {
        return ordenJpaRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, Orden.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        ordenJpaRepository.deleteById(id);
    }

    @Override
    public List<Orden> findAllByIdComerciante(int idComerciante) {
        return ordenJpaRepository.findAllByIdComerciante(idComerciante).stream()
                .map(entity -> modelMapper.map(entity, Orden.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Orden> findByIdAndComerciante(int idOrden, int idComerciante) {
        return Optional.ofNullable(ordenJpaRepository.findByIdAndComerciante(idOrden, idComerciante))
                .map(entity -> modelMapper.map(entity, Orden.class));
    }
}
