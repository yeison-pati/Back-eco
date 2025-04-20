package com.itm.ecosurprise.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "puntuacion")
public class Puntuacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPuntuacion;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    @JsonIgnoreProperties(value = {
		"correo", "contrasena", "telefono", "rol", "nit", "rut", "productos", "sedes"
	  })
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    @JsonIgnoreProperties(value = {
		"descripcion", "precio", "puntuaciones"
	  })
    private Producto producto;

    private int puntos;
}