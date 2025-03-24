package com.itm.ecosurprise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Fecha {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int anio;
	private int mes;
	private int dia;

}
