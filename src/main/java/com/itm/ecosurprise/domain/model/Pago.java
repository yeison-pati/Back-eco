package com.itm.ecosurprise.domain.model;

public class Pago {

    private int idPago;
    private Orden orden;
    private Fecha fechaPago;
    private String estadoPago;
    private String metodoPago;
    private int montoPagado;

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Fecha getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Fecha fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public int getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(int montoPagado) {
        this.montoPagado = montoPagado;
    }
}
