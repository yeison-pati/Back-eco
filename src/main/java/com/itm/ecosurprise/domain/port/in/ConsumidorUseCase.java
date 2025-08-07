package com.itm.ecosurprise.domain.port.in;

import com.itm.ecosurprise.domain.model.Consumidor;

import java.util.List;
import java.util.Optional;

public interface ConsumidorUseCase {
    List<Consumidor> getAll();
    Optional<Consumidor> getById(int id);
    void deleteById(int id);
    Consumidor update(Consumidor consumidor);
}
