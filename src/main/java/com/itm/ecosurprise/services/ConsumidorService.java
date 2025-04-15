package com.itm.ecosurprise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itm.ecosurprise.models.Consumidor;
import com.itm.ecosurprise.repositories.IConsumidor;

@Service
public class ConsumidorService {

    @Autowired
    private IConsumidor consumidorRepository;

    
    public String saludar(){
        return "Hola desde el servicio de Consumidor";
    }



    public Consumidor getConsumidor(Long id){
        return consumidorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consumidor no encontrado con ID: " + id));
    }



    public Consumidor saveConsumidor(Consumidor consumidor) {
        return consumidorRepository.save(consumidor);
    }



    public String eliminarConsumidor(Long id) {
        consumidorRepository.deleteById(id);
        return "Consumidor eliminado con éxito";
    }



    public String actualizarConsumidor(Long id, Consumidor consumidor) {
        Consumidor consumidorexistente = consumidorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consumidor no encontrado con ID: " + id));
        consumidorexistente.setNombre(consumidor.getNombre());
        consumidorexistente.setCorreo(consumidor.getCorreo());
        consumidorexistente.setContrasena(consumidor.getContrasena());
        consumidorexistente.setCelulares(consumidor.getCelulares());
        consumidorexistente.setDirecciones(consumidor.getDirecciones());
        consumidorexistente.setRol(consumidor.getRol());
        consumidorexistente.setPuntos(consumidor.getPuntos());

        
        consumidorRepository.save(consumidorexistente);

        return "Consumidor actualizado con éxito";
    }

}
