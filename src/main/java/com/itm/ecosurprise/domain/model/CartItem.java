package com.itm.ecosurprise.domain.model;

import lombok.Data;

//lombok genera getters y setters
@Data
public class CartItem {
    private Producto producto;
    private int cantidad;
}
