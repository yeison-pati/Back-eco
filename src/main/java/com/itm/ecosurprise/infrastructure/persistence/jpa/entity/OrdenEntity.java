package com.itm.ecosurprise.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "orden")
public class OrdenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrden;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    @JsonIgnoreProperties(value = {
		"correo", "contrasena", "telefono", "rol", "nit", "rut", "productos", "sedes"
	  })
    private ConsumidorEntity consumidor;

    @ManyToOne
    @JoinColumn(name = "idFecha")
    private FechaEntity fechaOrden;

    private int montoTotal;

    @ManyToOne
    @JoinColumn(name = "idDireccion")
    private DireccionEntity direccionEntrega;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {
		"comerciante", "descripcion", "puntuaciones"
	  })
    private List<OrdenProductoEntity> productos;

    private String estadoOrden = "pendiente";

    @OneToOne(mappedBy = "orden", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {
		"orden"
	  })
    @EqualsAndHashCode.Exclude
    private PagoEntity pago;
}
