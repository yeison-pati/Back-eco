package com.itm.ecosurprise.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CarritoDTO {
    private List<ProductoDTO> productos = new ArrayList<>();
    private int total = 0;
}
