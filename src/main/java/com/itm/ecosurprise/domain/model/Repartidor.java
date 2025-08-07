package com.itm.ecosurprise.domain.model;

public class Repartidor extends Usuario {

	private String placa;
	private String soat;
	private String licencia;
	private String tecno;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getSoat() {
		return soat;
	}

	public void setSoat(String soat) {
		this.soat = soat;
	}

	public String getLicencia() {
		return licencia;
	}

	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}

	public String getTecno() {
		return tecno;
	}

	public void setTecno(String tecno) {
		this.tecno = tecno;
	}
}
