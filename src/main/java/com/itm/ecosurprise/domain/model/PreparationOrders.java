package com.itm.ecosurprise.domain.model;

import java.util.List;
import java.util.ArrayList;

public class PreparationOrders {
    private List<Orden> ordenes = new ArrayList<>();

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<Orden> ordenes) {
        this.ordenes = ordenes;
    }
}
