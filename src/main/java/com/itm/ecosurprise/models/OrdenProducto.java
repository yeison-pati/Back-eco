package com.itm.ecosurprise.models;

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
    private Orden orden;

    @OneToOne
    private Producto producto;

}
