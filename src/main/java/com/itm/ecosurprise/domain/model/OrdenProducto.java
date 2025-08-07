package com.itm.ecosurprise.domain.model;

public class OrdenProducto {

    private int idOrdenProducto;
    private Orden orden;
    private Producto producto;

    public int getIdOrdenProducto() {
        return idOrdenProducto;
    }

    public void setIdOrdenProducto(int idOrdenProducto) {
        this.idOrdenProducto = idOrdenProducto;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
