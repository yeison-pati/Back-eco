package com.itm.ecosurprise.domain.port.out;

import com.itm.ecosurprise.domain.model.Reporte;

import java.util.Optional;
import java.util.List;

public interface ReporteRepository {
    Reporte save(Reporte reporte);
    Optional<Reporte> findById(Integer id);
    List<Reporte> findAll();
    void deleteById(Integer id);
}
