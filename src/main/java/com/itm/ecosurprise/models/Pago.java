package com.itm.ecosurprise.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPago;

    @OneToOne
    @JoinColumn(name = "idOrden")
    @JsonIgnoreProperties(value = {
		"direccionEntrega", "productos", "estadoOrden", "pago"
	  })
    private Orden orden;

    @OneToOne
    private Fecha fechaPago;
    private String estadoPago;
    private String metodoPago;
    private float montoPagado;
}

