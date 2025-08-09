package com.itm.ecosurprise.domain.model;

import lombok.Data;

//lombok genera getters y setters
@Data
public class Telefono {
    private int idTelefono;
    private String indicativo;
    private String numero;
    private Usuario usuario;
}
