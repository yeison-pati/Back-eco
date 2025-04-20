
package com.itm.ecosurprise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itm.ecosurprise.models.Pago;
import com.itm.ecosurprise.repositories.IPago;

@Service
public class PagoService {
    @Autowired
    private IPago pagoRepository;
    
    public Pago crear(Pago fecha) {
            return pagoRepository.save(fecha);
    }

    public Pago obtenerXID(int idPago) {
        return pagoRepository.findById(idPago)
            .orElseThrow(()-> new RuntimeException("Pago no encontrado con ID: " + idPago));
    }

    public Pago actualizar(Pago pago) {
        Pago pagoExistente = pagoRepository.findById(pago.getIdPago())
            .orElseThrow(()-> new RuntimeException("Pago no encontrado con ID: " + pago.getIdPago()));
        pagoExistente.setEstadoPago(pago.getEstadoPago());
        pagoExistente.setMontoPagado(pago.getMontoPagado());
        return pagoRepository.save(pago);
    }

    public String eliminar(int id) {
        pagoRepository.deleteById(id);
        return "Direccion eliminada con éxito";
    }
}
