package com.itm.ecosurprise.domain.model;

import lombok.Data;

//lombok genera getters y setters
@Data
public abstract class Usuario {
    private int idUsuario;
    private String imagen;
    private String nombre;
    private String correo;
    private String contrasena;
    private Telefono telefono;
    private String rol;
}
