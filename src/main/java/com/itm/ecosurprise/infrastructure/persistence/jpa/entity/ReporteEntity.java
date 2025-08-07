package com.itm.ecosurprise.infrastructure.persistence.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reporte")
public class ReporteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReporte")
    private int idReporte;
    @OneToOne
    @JoinColumn(name = "idUsuario")
    @JsonIgnoreProperties(value = {
		"correo", "contrasena", "telefono", "rol", "nit", "rut", "productos", "sedes"
	  })
    private UsuarioEntity Usuario;
    private String descripcion;
}
