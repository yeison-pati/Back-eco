package com.itm.ecosurprise.domain.model;

public class UsuarioDireccion {

    private int idUsuarioDireccion;
    private Usuario usuario;
    private Direccion direccion;

    public int getIdUsuarioDireccion() {
        return idUsuarioDireccion;
    }

    public void setIdUsuarioDireccion(int idUsuarioDireccion) {
        this.idUsuarioDireccion = idUsuarioDireccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
