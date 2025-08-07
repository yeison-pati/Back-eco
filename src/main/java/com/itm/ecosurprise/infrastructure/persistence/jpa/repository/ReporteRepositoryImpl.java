package com.itm.ecosurprise.infrastructure.persistence.jpa.repository;

import com.itm.ecosurprise.domain.model.Reporte;
import com.itm.ecosurprise.domain.port.out.ReporteRepository;
import com.itm.ecosurprise.infrastructure.persistence.jpa.entity.ReporteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ReporteRepositoryImpl implements ReporteRepository {

    private final ReporteJpaRepository reporteJpaRepository;
    private final ModelMapper modelMapper;

    public ReporteRepositoryImpl(ReporteJpaRepository reporteJpaRepository, ModelMapper modelMapper) {
        this.reporteJpaRepository = reporteJpaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Reporte save(Reporte domain) {
        ReporteEntity entity = modelMapper.map(domain, ReporteEntity.class);
        ReporteEntity savedEntity = reporteJpaRepository.save(entity);
        return modelMapper.map(savedEntity, Reporte.class);
    }

    @Override
    public Optional<Reporte> findById(Integer id) {
        return reporteJpaRepository.findById(id).map(entity -> modelMapper.map(entity, Reporte.class));
    }

    @Override
    public List<Reporte> findAll() {
        return reporteJpaRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, Reporte.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        reporteJpaRepository.deleteById(id);
    }
}
