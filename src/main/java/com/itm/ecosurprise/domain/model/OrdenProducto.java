package com.itm.ecosurprise.domain.model;

import lombok.Data;

//lombok genera getters y setters
@Data
public class OrdenProducto {

    private int idOrdenProducto;
    private Orden orden;
    private Producto producto;
    
}
