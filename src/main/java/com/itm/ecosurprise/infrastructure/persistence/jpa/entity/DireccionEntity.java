package com.itm.ecosurprise.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "direcciones")
public class DireccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDireccion")
    private int idDireccion;

    private String nombre;
    private String domicilio;
}
