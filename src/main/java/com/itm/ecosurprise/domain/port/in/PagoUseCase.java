package com.itm.ecosurprise.domain.port.in;

import com.itm.ecosurprise.domain.model.Pago;

import java.util.List;
import java.util.Optional;

public interface PagoUseCase {
    List<Pago> getAll();
    Optional<Pago> getById(int id);
    Pago create(Pago pago);
    Pago update(Pago pago);
    void deleteById(int id);
}
