package com.itm.ecosurprise.interfaces;

import com.itm.ecosurprise.models.Orden;

public interface EstadoOrdenState {
    void confirmar(Orden orden);
    void cancelar(Orden orden);
    void reembolsar(Orden orden);
}

