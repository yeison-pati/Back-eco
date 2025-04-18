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
    @JsonBackReference // Comerciante gestiona la lista de Productos
    private Comerciante comerciante;

    private String nombre;
    private String descripcion;
    private double precio;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    @JsonManagedReference // Producto gestiona la lista de Puntuacion
    private List<Puntuacion> puntuaciones;
}
