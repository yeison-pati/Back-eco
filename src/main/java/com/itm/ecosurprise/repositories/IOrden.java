package com.itm.ecosurprise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itm.ecosurprise.models.Orden;

public interface IOrden extends JpaRepository<Orden, Integer> {

}
