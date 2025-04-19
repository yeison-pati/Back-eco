package com.itm.ecosurprise.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sedes")
public class Sede {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSede;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idDireccion")
	private Direccion direccion;

	private String horario;

	@ManyToOne
	@JoinColumn(name = "idUsuario")
	@JsonIgnore
	private Comerciante comerciante;

}

/*
 * Properties(value = {
		"correo", "contrasena", "telefono", "rol", "nit", "rut", "productos", "sedes"
	  })
 */
