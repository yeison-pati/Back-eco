package com.itm.ecosurprise.infrastructure.persistence.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "puntuacion")
public class PuntuacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPuntuacion;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    @JsonIgnoreProperties(value = {
		"correo", "contrasena", "telefono", "rol", "nit", "rut", "productos", "sedes"
	  })
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    @JsonIgnoreProperties(value = {
		"descripcion", "precio", "puntuaciones"
	  })
    private ProductoEntity producto;

    private int puntos;
}
