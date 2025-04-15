package com.itm.ecosurprise.models;

import com.itm.ecosurprise.enums.TipoDireccion;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "entregaDireccion")
@Data
public class EntregaDireccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntregaDireccion;

    @ManyToOne
    private Entrega entrega;

    @ManyToOne
    private Direccion direccion;

    private TipoDireccion tipo;
}
