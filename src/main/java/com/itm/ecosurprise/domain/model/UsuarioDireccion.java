package com.itm.ecosurprise.domain.model;

import lombok.Data;

//lombok genera getters y setters
@Data
public class UsuarioDireccion {

    private int idUsuarioDireccion;
    private Usuario usuario;
    private Direccion direccion;
}
