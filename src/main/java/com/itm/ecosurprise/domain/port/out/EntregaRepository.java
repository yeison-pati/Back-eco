package com.itm.ecosurprise.domain.port.out;

import com.itm.ecosurprise.domain.model.Entrega;

import java.util.Optional;
import java.util.List;

public interface EntregaRepository {
    Entrega save(Entrega entrega);
    Optional<Entrega> findById(Integer id);
    List<Entrega> findAll();
    void deleteById(Integer id);
}
