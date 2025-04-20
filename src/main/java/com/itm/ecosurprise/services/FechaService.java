package com.itm.ecosurprise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itm.ecosurprise.models.Fecha;
import com.itm.ecosurprise.repositories.IFecha;

@Service
public class FechaService {
    @Autowired
    private IFecha fechaRepository;
    
    public Fecha crear(Fecha fecha) {
            return fechaRepository.save(fecha);
    }

    public String eliminar(int id) {
        fechaRepository.deleteById(id);
        return "Direccion eliminada con éxito";
    }
}
