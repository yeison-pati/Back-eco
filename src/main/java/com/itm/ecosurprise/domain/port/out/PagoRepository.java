package com.itm.ecosurprise.domain.port.out;

import com.itm.ecosurprise.domain.model.Pago;

import java.util.Optional;
import java.util.List;

public interface PagoRepository {
    Pago save(Pago pago);
    Optional<Pago> findById(Integer id);
    List<Pago> findAll();
    void deleteById(Integer id);
}
