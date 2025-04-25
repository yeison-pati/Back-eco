package com.itm.ecosurprise.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;


 @Data
 @Entity
 @Table(name = "telefonos")
 public class Telefono {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "idTelefono")
     private int idTelefono;
     private int indicativo;
     private int numero;
 
     @OneToOne
     @JoinColumn(name = "idUsuario")
     @JsonIgnoreProperties(value = {
		"correo", "contrasena", "telefono", "rol", "nit", "rut", "productos",
    "sedes", "direcciones", "puntos", "ordenes", "imagen", "tipo"
	  })
     private Usuario usuario;
 }
