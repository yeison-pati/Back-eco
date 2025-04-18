package com.itm.ecosurprise.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sedes")
public class Sede {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSede;

	@OneToOne
	@JsonManagedReference
	private Direccion direccion;

	private String horario;

	@ManyToOne
	@JoinColumn(name = "idUsuario")
	@JsonBackReference
	private Comerciante comerciante;

}
