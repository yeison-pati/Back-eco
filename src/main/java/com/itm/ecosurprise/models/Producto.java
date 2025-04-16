package com.itm.ecosurprise.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Comerciante Comerciante;
    private String nombre;
    private String descripcion;
    private double precio;

    @OneToMany
    private List<Puntuacion> puntuaciones;
}

