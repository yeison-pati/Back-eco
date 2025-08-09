package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.Pago;
import com.itm.ecosurprise.domain.port.in.PagoUseCase;
import com.itm.ecosurprise.domain.port.out.PagoRepository;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

//auto bean y auto inyeccion de dependencias con service
@Service
//es para crear el constructor con todos los atributos
@AllArgsConstructor
public class PagoApplicationService implements PagoUseCase {

    private final PagoRepository pagoRepository;

    @Override
    public List<Pago> getAll() {
        return pagoRepository.findAll();
    }

    @Override
    public Optional<Pago> getById(int id) {
        return pagoRepository.findById(id);
    }

    @Override
    public Pago create(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public Pago update(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public void deleteById(int id) {
        pagoRepository.deleteById(id);
    }
}
