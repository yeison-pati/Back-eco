package com.itm.ecosurprise.infrastructure.persistence.jpa.repository;

import com.itm.ecosurprise.domain.model.Producto;
import com.itm.ecosurprise.domain.port.out.ProductoRepository;
import com.itm.ecosurprise.infrastructure.persistence.jpa.entity.ProductoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductoRepositoryImpl implements ProductoRepository {

    private final ProductoJpaRepository productoJpaRepository;
    private final ModelMapper modelMapper;

    public ProductoRepositoryImpl(ProductoJpaRepository productoJpaRepository, ModelMapper modelMapper) {
        this.productoJpaRepository = productoJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Producto save(Producto domain) {
        ProductoEntity entity = modelMapper.map(domain, ProductoEntity.class);
        ProductoEntity savedEntity = productoJpaRepository.save(entity);
        return modelMapper.map(savedEntity, Producto.class);
    }

    @Override
    public Optional<Producto> findById(Integer id) {
        return productoJpaRepository.findById(id).map(entity -> modelMapper.map(entity, Producto.class));
    }

    @Override
    public List<Producto> findAll() {
        return productoJpaRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, Producto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        productoJpaRepository.deleteById(id);
    }
}
