package com.itm.ecosurprise.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne
    @JoinColumn(name = "idProducto")
    @JsonBackReference
    private Producto producto;
    
    private int puntos;
}