package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.Pago;
import com.itm.ecosurprise.domain.port.in.PagoUseCase;
import com.itm.ecosurprise.domain.port.out.PagoRepository;

import java.util.List;
import java.util.Optional;

public class PagoApplicationService implements PagoUseCase {

    private final PagoRepository pagoRepository;

    public PagoApplicationService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @Override
    public List<Pago> getAll() {
        return pagoRepository.findAll();
    }

    @Override
    public Optional<Pago> getById(int id) {
        return pagoRepository.findById(id);
    }

    @Override
    public Pago create(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public Pago update(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public void deleteById(int id) {
        pagoRepository.deleteById(id);
    }
}
