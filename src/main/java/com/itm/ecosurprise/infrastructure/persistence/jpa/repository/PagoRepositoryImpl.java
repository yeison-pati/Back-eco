package com.itm.ecosurprise.infrastructure.persistence.jpa.repository;

import com.itm.ecosurprise.domain.model.Pago;
import com.itm.ecosurprise.domain.port.out.PagoRepository;
import com.itm.ecosurprise.infrastructure.persistence.jpa.entity.PagoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PagoRepositoryImpl implements PagoRepository {

    private final PagoJpaRepository pagoJpaRepository;
    private final ModelMapper modelMapper;

    public PagoRepositoryImpl(PagoJpaRepository pagoJpaRepository, ModelMapper modelMapper) {
        this.pagoJpaRepository = pagoJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Pago save(Pago domain) {
        PagoEntity entity = modelMapper.map(domain, PagoEntity.class);
        PagoEntity savedEntity = pagoJpaRepository.save(entity);
        return modelMapper.map(savedEntity, Pago.class);
    }

    @Override
    public Optional<Pago> findById(Integer id) {
        return pagoJpaRepository.findById(id).map(entity -> modelMapper.map(entity, Pago.class));
    }

    @Override
    public List<Pago> findAll() {
        return pagoJpaRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, Pago.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        pagoJpaRepository.deleteById(id);
    }
}
