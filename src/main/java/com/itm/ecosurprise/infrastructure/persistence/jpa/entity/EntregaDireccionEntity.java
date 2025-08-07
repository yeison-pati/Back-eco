package com.itm.ecosurprise.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "entregaDireccion")
@Data
public class EntregaDireccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEntregaDireccion;

    @ManyToOne
    @JoinColumn(name = "idEntrega")
    private EntregaEntity entrega;

    @OneToOne
    private DireccionEntity direccion;

    private String tipo;
}
