package com.itm.ecosurprise.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "puntuacion")
public class Puntuacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPuntuacion;

    @OneToOne
    private Usuario usuario;

    @ManyToOne(optional = false)
    private Producto producto;
    
    private int puntos;
}

