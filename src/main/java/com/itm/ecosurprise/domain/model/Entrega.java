package com.itm.ecosurprise.domain.model;

import java.util.List;

import lombok.Data;

//lombok genera getters y setters
@Data
public class Entrega {

	private int idEntrega;
	private Orden orden;
	private Repartidor repartidor;
	private int numeroEntrega;
	private List<EntregaDireccion> direcciones;
	private String estadoEntrega;
}
