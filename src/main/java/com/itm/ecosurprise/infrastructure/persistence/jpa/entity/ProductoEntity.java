package com.itm.ecosurprise.infrastructure.persistence.jpa.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "producto")
public class ProductoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idProducto")
  private int idProducto;
  private String imagen;
  private String nombre;
  private int precio;
  private int stock;

  private String tipo;
  private String descripcion;

  @ManyToOne
  @JoinColumn(name = "idUsuario")
  @JsonIgnoreProperties(value = {
      "correo", "contrasena", "telefono", "rol", "nit", "rut", "productos", "sedes"
  })
  private ComercianteEntity comerciante;

  @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
  @JsonIgnoreProperties(value = {
      "usuario", "producto"
  })
  private List<PuntuacionEntity> puntuaciones;

}
