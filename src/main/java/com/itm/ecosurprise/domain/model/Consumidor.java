package com.itm.ecosurprise.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Consumidor extends Usuario {

    private int puntos;
    private List<UsuarioDireccion> direcciones = new ArrayList<>();
    private List<Orden> ordenes = new ArrayList<>();

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public List<UsuarioDireccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<UsuarioDireccion> direcciones) {
        this.direcciones = direcciones;
    }

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<Orden> ordenes) {
        this.ordenes = ordenes;
    }
}
