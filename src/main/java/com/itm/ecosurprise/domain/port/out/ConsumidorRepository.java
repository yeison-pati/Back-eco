package com.itm.ecosurprise.domain.port.out;

import com.itm.ecosurprise.domain.model.Consumidor;

import java.util.Optional;
import java.util.List;

public interface ConsumidorRepository {
    Consumidor save(Consumidor consumidor);
    Optional<Consumidor> findById(Integer id);
    List<Consumidor> findAll();
    void deleteById(Integer id);
}
