package com.itm.ecosurprise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Comerciante extends Usuario {

	private long NIT;
	private long RUT;
	@OneToMany
	private List<Sede> sedes;
}
