package com.itm.ecosurprise.domain.model;

import java.util.List;

import lombok.Data;

//lombok genera getters y setters
@Data
public class Producto {

  private int idProducto;
  private String imagen;
  private String nombre;
  private int precio;
  private int stock;
  private String tipo;
  private String descripcion;
  private Comerciante comerciante;
  private List<Puntuacion> puntuaciones;
}
