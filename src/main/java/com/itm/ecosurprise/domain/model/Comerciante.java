package com.itm.ecosurprise.domain.model;

import java.util.List;

public class Comerciante extends Usuario {

	private String nit;
	private String rut;
	private String camaraComercio;
	private List<Producto> productos;
	private Direccion direccion;

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getCamaraComercio() {
		return camaraComercio;
	}

	public void setCamaraComercio(String camaraComercio) {
		this.camaraComercio = camaraComercio;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
}
