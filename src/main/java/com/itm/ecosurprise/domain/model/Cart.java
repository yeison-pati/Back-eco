package com.itm.ecosurprise.domain.model;

import java.util.List;

public class Cart {
    private List<CartItem> items;
    private int total;

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
