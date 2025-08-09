package com.itm.ecosurprise.domain.model;

import lombok.Data;

//lombok genera getters y setters
@Data
public class Direccion {

    private int idDireccion;
    private String nombre;
    private String domicilio;
}
