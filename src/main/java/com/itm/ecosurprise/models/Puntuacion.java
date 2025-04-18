package com.itm.ecosurprise.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "puntuacion")
public class Puntuacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPuntuacion;

    @OneToOne
    private Usuario usuario;

    @OneToOne
    private Producto producto;
    
    private int puntos;
}