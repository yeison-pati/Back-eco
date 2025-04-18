package com.itm.ecosurprise.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto")
    private int idProducto;

    @ManyToOne
    @JoinColumn(name = "idComerciante")
    @JsonBackReference
    private Comerciante Comerciante;

    private String nombre;
    private String descripcion;
    private double precio;

    @OneToMany
    @JsonManagedReference
    private List<Puntuacion> puntuaciones;
}

