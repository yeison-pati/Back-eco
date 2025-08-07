package com.itm.ecosurprise.domain.model;

import java.util.List;

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

  public int getIdProducto() {
    return idProducto;
  }

  public void setIdProducto(int idProducto) {
    this.idProducto = idProducto;
  }

  public String getImagen() {
    return imagen;
  }

  public void setImagen(String imagen) {
    this.imagen = imagen;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getPrecio() {
    return precio;
  }

  public void setPrecio(int precio) {
    this.precio = precio;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Comerciante getComerciante() {
    return comerciante;
  }

  public void setComerciante(Comerciante comerciante) {
    this.comerciante = comerciante;
  }

  public List<Puntuacion> getPuntuaciones() {
    return puntuaciones;
  }

  public void setPuntuaciones(List<Puntuacion> puntuaciones) {
    this.puntuaciones = puntuaciones;
  }
}
