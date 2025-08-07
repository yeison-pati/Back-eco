package com.itm.ecosurprise.infrastructure.persistence.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ordenProducto")
public class OrdenProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrdenProducto;

    @ManyToOne
    @JoinColumn(name = "idOrden")
    @JsonIgnoreProperties(value = {
		"fechaOrden", "direccionEntrega", "productos", "estadoOrden", "pago", "consumidor"
    })
    private OrdenEntity orden;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    @JsonIgnoreProperties(value = {
        "comerciante", "descripcion", "puntuaciones"
    })
    private ProductoEntity producto;

}
