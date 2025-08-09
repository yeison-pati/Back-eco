package com.itm.ecosurprise.application;

import com.itm.ecosurprise.domain.model.Orden;
import com.itm.ecosurprise.domain.model.PreparationOrders;
import com.itm.ecosurprise.domain.port.in.OrderUseCase;
import com.itm.ecosurprise.domain.port.in.PreparationOrdersUseCase;
import com.itm.ecosurprise.domain.port.out.ComercianteRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;

//auto bean y auto inyeccion de dependencias con service
@Service
//es para crear el constructor con todos los atributos
@AllArgsConstructor
public class PreparationOrdersApplicationService implements PreparationOrdersUseCase {

    private final Map<Integer, PreparationOrders> preparationOrders = new HashMap<>();
    private final ComercianteRepository comercianteRepository;
    private final OrderUseCase orderUseCase;

    @Override
    public PreparationOrders getPreparationOrders(int idComerciante) {
        comercianteRepository.findById(idComerciante)
                .orElseThrow(() -> new RuntimeException("Comerciante no encontrado"));
        return preparationOrders.computeIfAbsent(idComerciante, id -> new PreparationOrders());
    }

    @Override
    public void addOrderToPreparation(int idComerciante, int idOrden) {
        PreparationOrders orders = getPreparationOrders(idComerciante);
        Orden orden = orderUseCase.confirm(idOrden);
        orders.getOrdenes().add(orden);
    }

    @Override
    public Orden getOrderFromPreparation(int idComerciante, int idOrden) {
        PreparationOrders orders = getPreparationOrders(idComerciante);
        return orders.getOrdenes().stream()
                .filter(o -> o.getIdOrden() == idOrden)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Orden no encontrada o no pertenece al comerciante"));
    }
}
