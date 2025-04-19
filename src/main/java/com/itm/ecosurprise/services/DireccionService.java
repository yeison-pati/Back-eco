package com.itm.ecosurprise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itm.ecosurprise.models.Direccion;
import com.itm.ecosurprise.repositories.IDireccion;

@Service
public class DireccionService {
    @Autowired
    private IDireccion direccionRepository;
    
    public Direccion crear(Direccion direccion) {
            return direccionRepository.save(direccion);
    }

    public String eliminar(int id) {
        direccionRepository.deleteById(id);
        return "Direccion eliminada con éxito";
    }
}
