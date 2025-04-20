package com.itm.ecosurprise.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    @JsonIgnoreProperties(value = {
		"fechaOrden", "direccionEntrega", "productos", "estadoOrden", "pago"
    })
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    @JsonIgnoreProperties(value = {
        "comerciante", "descripcion", "puntuaciones"
    })
    private Producto producto;

}