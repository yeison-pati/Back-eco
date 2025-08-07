package com.itm.ecosurprise.domain.port.out;

import com.itm.ecosurprise.domain.model.Repartidor;

import java.util.Optional;
import java.util.List;

public interface RepartidorRepository {
    Repartidor save(Repartidor repartidor);
    Optional<Repartidor> findById(Integer id);
    List<Repartidor> findAll();
    void deleteById(Integer id);
}
