package com.itm.ecosurprise.dtos;

import java.util.ArrayList;
import java.util.List;

import com.itm.ecosurprise.domain.model.Orden;

import lombok.Data;

@Data
public class OrdenesPrepDTO {
    private List<Orden> ordenes = new ArrayList<>();
}
