package com.itm.ecosurprise.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "entregaDireccion")
@Data
public class EntregaDireccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEntregaDireccion;

    @ManyToOne
    @JoinColumn(name = "idEntrega")
    private Entrega entrega;

    @OneToOne
    private Direccion direccion;

    private String tipo;
}
