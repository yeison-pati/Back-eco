package com.itm.ecosurprise.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itm.ecosurprise.models.Comerciante;
import com.itm.ecosurprise.models.Sede;
import com.itm.ecosurprise.repositories.IComerciante;
import com.itm.ecosurprise.repositories.ISede;

@Service
public class SedeService {

    @Autowired
    private ISede sedeRepository;
    @Autowired
    private IComerciante comercianteRepository;
    @Autowired
    private DireccionService direccionService;

    public List<Sede> obtenerTodos() {
        return sedeRepository.findAll();

    }

    public Sede obtenerXID(int id) {
        return sedeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sede no encontrada con ID: " + id));
    }

    public Sede crear(int idComerciante, Sede sede) {
        Comerciante comerciante = comercianteRepository.findById(idComerciante)
                .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));
        sede.setComerciante(comerciante);
        return sedeRepository.save(sede);
    }

    public Sede actualizar(Sede sede) {
        Sede sedeExistente = sedeRepository.findById(sede.getIdSede())
                .orElseThrow(() -> new RuntimeException("Sede no encontrada con ID: " + sede.getIdSede()));
        
        if (sede.getDireccion() != null) {
            direccionService.eliminar(sedeExistente.getDireccion().getIdDireccion());
            sedeExistente.setDireccion(direccionService.crear(sede.getDireccion()));
        }
        sedeExistente.setHorario(sede.getHorario());
        return sedeRepository.save(sede);

    }

    public String eliminar(int id) {

        sedeRepository.deleteById(id);
        return "Sede eliminada con éxito";
    }
}
