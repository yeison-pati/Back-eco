package com.itm.ecosurprise.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Data
@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrden;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    @JsonIgnoreProperties(value = {
		"correo", "contrasena", "telefono", "rol", "nit", "rut", "productos", "sedes"
	  })
    private Consumidor consumidor;

    @ManyToOne
    @JoinColumn(name = "idFecha")
    private Fecha fechaOrden;

    private int montoTotal;

    @ManyToOne
    @JoinColumn(name = "idDireccion")
    private Direccion direccionEntrega;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {
		"comerciante", "descripcion", "puntuaciones"
	  })
    private List<OrdenProducto> productos;

    private String estadoOrden = "pendiente";

    @OneToOne(mappedBy = "orden", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {
		"orden"
	  })
    private Pago pago;
}