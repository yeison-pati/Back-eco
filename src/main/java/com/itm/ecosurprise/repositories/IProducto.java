package com.itm.ecosurprise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itm.ecosurprise.models.Producto;

public interface IProducto extends JpaRepository<Producto, Long> {

}
