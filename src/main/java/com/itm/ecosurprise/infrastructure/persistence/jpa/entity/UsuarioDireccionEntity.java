package com.itm.ecosurprise.infrastructure.persistence.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarioDireccion")
public class UsuarioDireccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuarioDireccion")
    private int idUsuarioDireccion;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    @JsonIgnoreProperties(value = {
		"correo", "contrasena", "telefono", "rol", "nit", "rut",
    "productos", "sedes", "direcciones", "puntos", "ordenes", "imagen", "tipo"
	  })
    private UsuarioEntity usuario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idDireccion")
    private DireccionEntity direccion;
}
