package com.itm.ecosurprise.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Repartidor extends Usuario {
	private String placa;
	private long soat;
	private long licencia;
	private long tecno;
}
