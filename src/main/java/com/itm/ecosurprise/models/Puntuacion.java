package com.itm.ecosurprise.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "puntuacion")
public class Puntuacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPuntuacion;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    @JsonManagedReference // Un Usuario *da* puntuaciones
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    @JsonBackReference // Producto es la referencia *back* desde Puntuacion
    private Producto producto;

    private int puntos;
}