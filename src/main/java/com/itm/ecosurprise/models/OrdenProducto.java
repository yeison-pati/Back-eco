package com.itm.ecosurprise.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ordenProducto")
public class OrdenProducto {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int idOrdenProducto;

    @ManyToOne
    @JoinColumn(name = "idOrden")
    @JsonBackReference // Orden gestiona la lista de OrdenProducto
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

}