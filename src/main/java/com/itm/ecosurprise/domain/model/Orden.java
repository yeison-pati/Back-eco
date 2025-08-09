package com.itm.ecosurprise.domain.model;

import java.util.List;

import lombok.Data;

//lombok genera getters y setters
@Data
public class Orden {

    private int idOrden;
    private Consumidor consumidor;
    private Fecha fechaOrden;
    private int montoTotal;
    private Direccion direccionEntrega;
    private List<OrdenProducto> productos;
    private String estadoOrden = "pendiente";
    private Pago pago;
}
