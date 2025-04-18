package com.itm.ecosurprise.repositories;

import com.itm.ecosurprise.models.Repartidor;
import com.itm.ecosurprise.models.Sede;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISede extends JpaRepository<Sede, Integer> {

}
