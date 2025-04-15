package com.itm.ecosurprise.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sedes")
public class Sede {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idSede;

	@OneToOne
	private Direccion direccion;
	private String horario;

	@ManyToOne
	private Comerciante comerciante;

}
