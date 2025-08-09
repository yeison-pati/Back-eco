package com.itm.ecosurprise.domain.model;

import java.util.List;
import java.util.ArrayList;

import lombok.Data;

//lombok genera getters y setters
@Data
public class PreparationOrders {
    private List<Orden> ordenes = new ArrayList<>();
}
