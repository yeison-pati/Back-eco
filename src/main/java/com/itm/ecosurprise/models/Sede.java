package com.itm.ecosurprise.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Sede {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne(cascade = CascadeType.ALL)
	private Direccion direccion;
	private String horario;

}
