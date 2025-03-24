package com.itm.ecosurprise.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long idComerciante;
    private String nombre;
    private String descripcion;
    private double precio;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Puntuacion puntuacion;
}
