package com.itm.ecosurprise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/*
 * @EqualsAndHashCode(callSuper = true) permite usar el constructor de la clase padre
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "repartidores")
public class Repartidor extends Usuario {

	private String placa;
	private long soat;
	private long licencia;
	private long tecno;
}
