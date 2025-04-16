package com.itm.ecosurprise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import jakarta.persistence.Table;

/*
 * @EqualsAndHashCode(callSuper = true) permite usar el constructor de la clase padre
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "comerciantes")
public class Comerciante extends Usuario {

	private int nit;
	private int rut;
	
	@OneToMany
	private List<Sede> sedes;
}
