package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.Reporte;
import com.itm.ecosurprise.domain.port.in.ReporteUseCase;
import com.itm.ecosurprise.domain.port.out.ReporteRepository;

import java.util.List;
import java.util.Optional;

public class ReporteApplicationService implements ReporteUseCase {

    private final ReporteRepository reporteRepository;

    public ReporteApplicationService(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    @Override
    public List<Reporte> getAll() {
        return reporteRepository.findAll();
    }

    @Override
    public Optional<Reporte> getById(int id) {
        return reporteRepository.findById(id);
    }

    @Override
    public Reporte create(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    @Override
    public Reporte update(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    @Override
    public void deleteById(int id) {
        reporteRepository.deleteById(id);
    }
}
