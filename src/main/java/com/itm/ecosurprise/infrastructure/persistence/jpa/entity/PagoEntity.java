package com.itm.ecosurprise.infrastructure.persistence.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "pago")
public class PagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPago;

    @OneToOne
    @JoinColumn(name = "idOrden")
    @JsonIgnoreProperties(value = {
		"direccionEntrega", "productos", "estadoOrden", "pago"
	  })
    @EqualsAndHashCode.Exclude
    private OrdenEntity orden;

    @OneToOne
    private FechaEntity fechaPago;
    private String estadoPago;
    private String metodoPago;
    private int montoPagado;
}
