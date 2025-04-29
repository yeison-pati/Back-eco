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

    /**
     * Obtiene una lista de todas las sedes almacenadas en el repositorio.
     *
     * @return Una lista de objetos `Sede`.
     */
    public List<Sede> obtenerTodos() {
        return sedeRepository.findAll();
    }

    /**
     * Obtiene una sede por su ID.
     *
     * @param id El ID de la sede a obtener.
     * @return El objeto `Sede` correspondiente al ID proporcionado.
     * @throws RuntimeException Si no se encuentra la sede con el ID dado.
     */
    public Sede obtenerXID(int id) {
        return sedeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sede no encontrada con ID: " + id));
    }

    /**
     * Crea una nueva sede y la asocia a un comerciante.
     *
     * @param idComerciante El ID del comerciante al que se asignará la sede.
     * @param sede El objeto `Sede` a crear.
     * @return El objeto `Sede` creado y guardado en la base de datos.
     * @throws RuntimeException Si no se encuentra el comerciante con el ID dado.
     */
    public Sede crear(int idComerciante, Sede sede) {
        Comerciante comerciante = comercianteRepository.findById(idComerciante)
                .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));
        sede.setComerciante(comerciante);
        return sedeRepository.save(sede);
    }

    /**
     * Actualiza la información de una sede existente.
     *
     * @param sede El objeto `Sede` con los nuevos valores a actualizar.
     * @return El objeto `Sede` actualizado.
     * @throws RuntimeException Si no se encuentra la sede con el ID proporcionado.
     */
    public Sede actualizar(Sede sede) {
        Sede sedeExistente = sedeRepository.findById(sede.getIdSede())
                .orElseThrow(() -> new RuntimeException("Sede no encontrada con ID: " + sede.getIdSede()));
        
        // Si la dirección de la sede ha cambiado, se actualiza.
        if (sede.getDireccion() != null) {
            direccionService.eliminar(sedeExistente.getDireccion().getIdDireccion());
            sedeExistente.setDireccion(direccionService.crear(sede.getDireccion()));
        }
        sedeExistente.setHorario(sede.getHorario());
        return sedeRepository.save(sede);
    }

    /**
     * Elimina una sede por su ID.
     *
     * @param id El ID de la sede a eliminar.
     * @return Un mensaje indicando que la sede ha sido eliminada con éxito.
     */
    public String eliminar(int id) {
        sedeRepository.deleteById(id);
        return "Sede eliminada con éxito";
    }
}
