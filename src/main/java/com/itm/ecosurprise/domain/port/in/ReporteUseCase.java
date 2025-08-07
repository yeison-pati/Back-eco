package com.itm.ecosurprise.domain.port.in;

import com.itm.ecosurprise.domain.model.Reporte;

import java.util.List;
import java.util.Optional;

public interface ReporteUseCase {
    List<Reporte> getAll();
    Optional<Reporte> getById(int id);
    Reporte create(Reporte reporte);
    Reporte update(Reporte reporte);
    void deleteById(int id);
}
