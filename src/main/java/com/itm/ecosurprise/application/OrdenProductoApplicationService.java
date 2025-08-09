package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.OrdenProducto;
import com.itm.ecosurprise.domain.port.in.OrdenProductoUseCase;
import com.itm.ecosurprise.domain.port.out.OrdenProductoRepository;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

//auto bean y auto inyeccion de dependencias con service
@Service
//es para crear el constructor con todos los atributos
@AllArgsConstructor
public class OrdenProductoApplicationService implements OrdenProductoUseCase {

    private final OrdenProductoRepository ordenProductoRepository;

    @Override
    public List<OrdenProducto> getAll() {
        return ordenProductoRepository.findAll();
    }

    @Override
    public Optional<OrdenProducto> getById(int id) {
        return ordenProductoRepository.findById(id);
    }

    @Override
    public OrdenProducto create(OrdenProducto ordenProducto) {
        return ordenProductoRepository.save(ordenProducto);
    }

    @Override
    public OrdenProducto update(OrdenProducto ordenProducto) {
        return ordenProductoRepository.save(ordenProducto);
    }

    @Override
    public void deleteById(int id) {
        ordenProductoRepository.deleteById(id);
    }
}
