package com.itm.ecosurprise.domain.port.in;

import com.itm.ecosurprise.domain.model.Entrega;

import java.util.List;
import java.util.Optional;

public interface EntregaUseCase {
    List<Entrega> getAll();
    Optional<Entrega> getById(int id);
    Entrega create(Entrega entrega);
    Entrega update(Entrega entrega);
    void deleteById(int id);
}
