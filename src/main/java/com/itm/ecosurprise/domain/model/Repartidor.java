package com.itm.ecosurprise.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

//lombok genera getters y setters
@Data
//si no quiero que dos comerciantes tengan el mismo id de su clase padre, callsuper debe ser true
@EqualsAndHashCode(callSuper = true)
public class Repartidor extends Usuario {

	private String placa;
	private String soat;
	private String licencia;
	private String tecno;
}
