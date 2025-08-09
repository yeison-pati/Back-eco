package com.itm.ecosurprise.domain.model;

import java.util.ArrayList;
import java.util.List;


import lombok.Data;
import lombok.EqualsAndHashCode;

//lombok genera getters y setters
@Data
//si no quiero que dos comerciantes tengan el mismo id de su clase padre, callsuper debe ser true
@EqualsAndHashCode(callSuper = true)
public class Consumidor extends Usuario {

    private int puntos;
    private List<UsuarioDireccion> direcciones = new ArrayList<>();
    private List<Orden> ordenes = new ArrayList<>();
}
