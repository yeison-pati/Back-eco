package com.itm.ecosurprise.domain.model;

import lombok.Data;

//lombok genera getters y setters
@Data
public class Puntuacion {

    private int idPuntuacion;
    private Usuario usuario;
    private Producto producto;
    private int puntos;
}
