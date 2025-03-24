package com.itm.ecosurprise.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Consumidor extends Usuario {
    private int puntos;
}
