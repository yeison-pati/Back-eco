package com.itm.ecosurprise.classes;

import com.itm.ecosurprise.interfaces.EstadoOrdenState;
import com.itm.ecosurprise.models.Orden;

public class EstadoCancelada implements EstadoOrdenState {

    @Override
    public void confirmar(Orden orden) {
        throw new UnsupportedOperationException("No se puede confirmar una orden cancelada.");
    }

    @Override
    public void cancelar(Orden orden) {
        throw new UnsupportedOperationException("La orden ya está cancelada.");
    }

    @Override
    public void reembolsar(Orden orden) {
        throw new UnsupportedOperationException("No se puede reembolsar una orden cancelada directamente.");
    }
}
