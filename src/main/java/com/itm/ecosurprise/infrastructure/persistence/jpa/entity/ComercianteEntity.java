package com.itm.ecosurprise.infrastructure.persistence.jpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "comerciantes")
public class ComercianteEntity extends UsuarioEntity {

	private String nit;
	private String rut;
	private String camaraComercio;
	
	@OneToMany(mappedBy = "comerciante", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = {
		"comerciante", "puntuaciones"
	  })
	private List<ProductoEntity> productos;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idDireccion")
	private DireccionEntity direccion;
}
