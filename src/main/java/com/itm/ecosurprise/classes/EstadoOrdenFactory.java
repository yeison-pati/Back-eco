package com.itm.ecosurprise.classes;

import com.itm.ecosurprise.enums.EstadoOrden;
import com.itm.ecosurprise.interfaces.EstadoOrdenState;
import com.itm.ecosurprise.models.Orden;

public class EstadoOrdenFactory {

    public static EstadoOrdenState getEstado(Orden orden) {
        switch (EstadoOrden.valueOf(orden.getEstadoOrden())) {
            case pendiente:
                return new EstadoPendiente();
            case comfirmada:
                return new EstadoConfirmada();
            case cancelada:
                return new EstadoCancelada(); // si la implementas
            case reembolsada:
                return new EstadoReembolsada(); // si la implementas
            default:
                throw new IllegalArgumentException("Estado desconocido");
        }
    }
}

