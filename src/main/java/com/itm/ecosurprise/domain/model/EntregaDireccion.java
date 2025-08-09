package com.itm.ecosurprise.domain.model;

import lombok.Data;

//lombok genera getters y setters
@Data
public class EntregaDireccion {

    private int idEntregaDireccion;
    private Entrega entrega;
    private Direccion direccion;
    private String tipo;
}
