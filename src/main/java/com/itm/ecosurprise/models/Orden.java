package com.itm.ecosurprise.models;

import com.itm.ecosurprise.enums.EstadoOrden;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Orden {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long idConsumidor;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Fecha fechaOrden;
	private double montoTotal;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Direccion direccionEntrega;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Producto> productos;
	private EstadoOrden estadoOrden;
	@OneToOne
	private Pago pago;

}
