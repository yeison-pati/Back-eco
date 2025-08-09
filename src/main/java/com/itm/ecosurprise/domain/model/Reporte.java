package com.itm.ecosurprise.domain.model;

import lombok.Data;

//lombok genera getters y setters
@Data
public class Reporte {

    private int idReporte;
    private Usuario usuario;
    private String descripcion;
}
