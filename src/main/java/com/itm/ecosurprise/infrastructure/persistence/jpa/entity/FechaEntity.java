package com.itm.ecosurprise.infrastructure.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "fecha")
public class FechaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFecha;
    private int anio;
    private int mes;
    private int dia;
}
