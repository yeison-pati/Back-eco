package com.itm.ecosurprise.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
	private Direccion direccion;
	private String horario;

	@ManyToOne
	@JsonBackReference
	private Comerciante comerciante;

}
