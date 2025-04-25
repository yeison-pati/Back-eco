package com.itm.ecosurprise.services;

import com.itm.ecosurprise.models.OrdenProducto;
import com.itm.ecosurprise.repositories.IOrdenProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenProductoService {
    @Autowired
    private IOrdenProducto ordenProductoRepository;

    public OrdenProducto crear(OrdenProducto ordenProducto) {
        return ordenProductoRepository.save(ordenProducto);
    }
}
