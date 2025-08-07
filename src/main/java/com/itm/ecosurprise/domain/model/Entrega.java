package com.itm.ecosurprise.domain.model;

import java.util.List;

public class Entrega {

	private int idEntrega;
	private Orden orden;
	private Repartidor repartidor;
	private int numeroEntrega;
	private List<EntregaDireccion> direcciones;
	private String estadoEntrega;

	public int getIdEntrega() {
		return idEntrega;
	}

	public void setIdEntrega(int idEntrega) {
		this.idEntrega = idEntrega;
	}

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

	public Repartidor getRepartidor() {
		return repartidor;
	}

	public void setRepartidor(Repartidor repartidor) {
		this.repartidor = repartidor;
	}

	public int getNumeroEntrega() {
		return numeroEntrega;
	}

	public void setNumeroEntrega(int numeroEntrega) {
		this.numeroEntrega = numeroEntrega;
	}

	public List<EntregaDireccion> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(List<EntregaDireccion> direcciones) {
		this.direcciones = direcciones;
	}

	public String getEstadoEntrega() {
		return estadoEntrega;
	}

	public void setEstadoEntrega(String estadoEntrega) {
		this.estadoEntrega = estadoEntrega;
	}
}
