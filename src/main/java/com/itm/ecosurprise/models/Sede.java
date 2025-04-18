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
	@JsonManagedReference // Sede gestiona la Direccion
	private Direccion direccion;

	private String horario;

	@ManyToOne
	@JoinColumn(name = "idUsuario")
	@JsonBackReference // Comerciante gestiona la lista de Sedes
	private Comerciante comerciante;

}
