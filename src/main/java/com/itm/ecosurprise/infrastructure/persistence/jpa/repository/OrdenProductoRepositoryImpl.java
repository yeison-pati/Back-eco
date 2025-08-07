package com.itm.ecosurprise.infrastructure.persistence.jpa.repository;

import com.itm.ecosurprise.domain.model.OrdenProducto;
import com.itm.ecosurprise.domain.port.out.OrdenProductoRepository;
import com.itm.ecosurprise.infrastructure.persistence.jpa.entity.OrdenProductoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrdenProductoRepositoryImpl implements OrdenProductoRepository {

    private final OrdenProductoJpaRepository ordenProductoJpaRepository;
    private final ModelMapper modelMapper;

    public OrdenProductoRepositoryImpl(OrdenProductoJpaRepository ordenProductoJpaRepository, ModelMapper modelMapper) {
        this.ordenProductoJpaRepository = ordenProductoJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrdenProducto save(OrdenProducto domain) {
        OrdenProductoEntity entity = modelMapper.map(domain, OrdenProductoEntity.class);
        OrdenProductoEntity savedEntity = ordenProductoJpaRepository.save(entity);
        return modelMapper.map(savedEntity, OrdenProducto.class);
    }

    @Override
    public Optional<OrdenProducto> findById(Integer id) {
        return ordenProductoJpaRepository.findById(id).map(entity -> modelMapper.map(entity, OrdenProducto.class));
    }

    @Override
    public List<OrdenProducto> findAll() {
        return ordenProductoJpaRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, OrdenProducto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        ordenProductoJpaRepository.deleteById(id);
    }
}
