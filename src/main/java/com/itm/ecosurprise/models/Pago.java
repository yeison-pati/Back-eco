package com.itm.ecosurprise.models;

import com.itm.ecosurprise.enums.EstadoPago;
import com.itm.ecosurprise.enums.MetodoPago;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Pago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long idTransaccion;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Fecha fechaPago;
	private EstadoPago estadoPago;
	private MetodoPago metodoPago;
	private float montoPagado;

}
