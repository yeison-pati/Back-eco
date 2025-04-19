package com.itm.ecosurprise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itm.ecosurprise.models.Consumidor;
import com.itm.ecosurprise.models.UsuarioDireccion;
import com.itm.ecosurprise.repositories.IConsumidor;
import com.itm.ecosurprise.repositories.IUsuarioDireccion;

@Service
public class UsuarioDireccionService {

    @Autowired
    private IConsumidor consumidorRepository;
    @Autowired
    private IUsuarioDireccion usuarioDireccionRepository;

    public UsuarioDireccion crear(int idUsuario) {

        UsuarioDireccion usuarioDireccion = new UsuarioDireccion();
        Consumidor consumidor = consumidorRepository.findById(idUsuario).
                orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuarioDireccion.setUsuario(consumidor);
        return usuarioDireccionRepository.save(usuarioDireccion);
    }
}
