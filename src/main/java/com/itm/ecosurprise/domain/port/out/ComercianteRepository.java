package com.itm.ecosurprise.domain.port.out;

import com.itm.ecosurprise.domain.model.Comerciante;

import java.util.Optional;
import java.util.List;

public interface ComercianteRepository {
    Comerciante save(Comerciante comerciante);
    Optional<Comerciante> findById(Integer id);
    List<Comerciante> findAll();
    void deleteById(Integer id);
}
