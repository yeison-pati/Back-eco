package com.itm.ecosurprise.domain.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

//lombok genera getters y setters
@Data
//si no quiero que dos comerciantes tengan el mismo id de su clase padre, callsuper debe ser true
@EqualsAndHashCode(callSuper = true)
public class Comerciante extends Usuario {
	private String nit;
	private String rut;
	private String camaraComercio;
	private List<Producto> productos;
	private Direccion direccion;
}
