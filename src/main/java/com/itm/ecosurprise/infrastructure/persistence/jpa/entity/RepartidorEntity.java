package com.itm.ecosurprise.infrastructure.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "repartidores")
public class RepartidorEntity extends UsuarioEntity {

	private String placa;
	private String soat;
	private String licencia;
	private String tecno;
}
