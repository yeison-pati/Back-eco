package com.itm.ecosurprise.domain.model;

public class EntregaDireccion {

    private int idEntregaDireccion;
    private Entrega entrega;
    private Direccion direccion;
    private String tipo;

    public int getIdEntregaDireccion() {
        return idEntregaDireccion;
    }

    public void setIdEntregaDireccion(int idEntregaDireccion) {
        this.idEntregaDireccion = idEntregaDireccion;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
