package com.itm.ecosurprise.domain.port.in;

import com.itm.ecosurprise.domain.model.Repartidor;

import java.util.List;
import java.util.Optional;

public interface RepartidorUseCase {
    List<Repartidor> getAll();
    Optional<Repartidor> getById(int id);
    Repartidor create(Repartidor repartidor);
    Repartidor update(Repartidor repartidor);
    void deleteById(int id);
}
