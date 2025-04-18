package com.itm.ecosurprise.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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

	@OneToMany(mappedBy = "comerciante", cascade = CascadeType.ALL)
	@JsonManagedReference // Comerciante gestiona la lista de Productos
	private List<Producto> productos;

	@OneToMany(mappedBy = "comerciante", cascade = CascadeType.ALL)
	@JsonManagedReference // Comerciante gestiona la lista de Sedes
	private List<Sede> sedes;
}
