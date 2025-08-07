package com.itm.ecosurprise.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "entregas")
public class EntregaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEntrega;

	@OneToOne
	@JsonIgnoreProperties(value = {
		"direccionEntrega","pago"
	  })
	private OrdenEntity orden;

	@OneToOne
	private RepartidorEntity repartidor;

	private int numeroEntrega;

	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<EntregaDireccionEntity> direcciones;

	private String estadoEntrega;

}
