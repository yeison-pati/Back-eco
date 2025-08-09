package com.itm.ecosurprise.domain.model;

import java.util.List;

import lombok.Data;

//lombok genera getters y setters
@Data
public class Cart {
    private List<CartItem> items;
    private int total;
}
