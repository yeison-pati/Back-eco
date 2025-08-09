package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.UsuarioDireccion;
import com.itm.ecosurprise.domain.port.in.UsuarioDireccionUseCase;
import com.itm.ecosurprise.domain.port.out.UsuarioDireccionRepository;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

//auto bean y auto inyeccion de dependencias con service
@Service
//es para crear el constructor con todos los atributos
@AllArgsConstructor
public class UsuarioDireccionApplicationService implements UsuarioDireccionUseCase {

    private final UsuarioDireccionRepository usuarioDireccionRepository;

    @Override
    public List<UsuarioDireccion> getAll() {
        return usuarioDireccionRepository.findAll();
    }

    @Override
    public Optional<UsuarioDireccion> getById(int id) {
        return usuarioDireccionRepository.findById(id);
    }

    @Override
    public UsuarioDireccion create(UsuarioDireccion usuarioDireccion) {
        return usuarioDireccionRepository.save(usuarioDireccion);
    }

    @Override
    public UsuarioDireccion update(UsuarioDireccion usuarioDireccion) {
        return usuarioDireccionRepository.save(usuarioDireccion);
    }

    @Override
    public void deleteById(int id) {
        usuarioDireccionRepository.deleteById(id);
    }
}
