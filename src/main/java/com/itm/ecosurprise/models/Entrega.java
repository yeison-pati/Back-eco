package com.itm.ecosurprise.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*
 * @Data genera metodos y contructores
 * @Entity indica que es una entidad de la base de datos
 * @Table indica el nombre de la tabla en la base de datos
 * @Column indica el nombre de la columna en la base de datos y si es nullable o unique
 * nullable indica la posibilidad de que el campo sea nulo
 * unique indica que el campo es unico
 * cascade = CascadeType.ALL: Indica que todas las operaciones 
 * (persist, merge, remove, etc.) realizadas en Usuario se propagarán 
 * automáticamente a las entidades relacionadas.
 * orphanRemoval = true: Si una entidad relacionada se elimina de la colección en comerciante,
 *      también se eliminará de la base de datos.
 */
@Data
@Entity
@Table(name = "entregas")
public class Entrega {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEntrega;

	@OneToOne
	@JsonIgnoreProperties(value = {
		"direccionEntrega","pago"
	  })
	private Orden orden;

	@OneToOne
	private Repartidor repartidor;

	private int numeroEntrega;

	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<EntregaDireccion> direcciones;

	private String estadoEntrega;

}
