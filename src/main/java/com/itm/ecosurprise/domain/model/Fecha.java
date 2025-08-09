package com.itm.ecosurprise.domain.model;

import lombok.Data;

//lombok genera getters y setters
@Data
public class Fecha {

    private int idFecha;
    private int anio;
    private int mes;
    private int dia;
}
