package com.itm.ecosurprise.models;

import com.itm.ecosurprise.enums.EstadoEntrega;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Entrega {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long idOrden;

	private long idComerciante;

	private long idRepartidor;
	private int numeroEntrega;
	@OneToMany
	private List<Direccion> paradas;
	@OneToOne
	private Direccion destino;
	private EstadoEntrega estadoEntrega;

}
