package com.itm.ecosurprise.domain.model;

import lombok.Data;

//lombok genera getters y setters
@Data
public class Pago {

    private int idPago;
    private Orden orden;
    private Fecha fechaPago;
    private String estadoPago;
    private String metodoPago;
    private int montoPagado;
}
