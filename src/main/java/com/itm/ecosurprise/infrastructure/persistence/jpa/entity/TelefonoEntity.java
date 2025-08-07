package com.itm.ecosurprise.infrastructure.persistence.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

 @Data
 @Entity
 @Table(name = "telefonos")
 public class TelefonoEntity {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "idTelefono")
     private int idTelefono;
     private String indicativo;
     private String numero;

     @OneToOne
     @JoinColumn(name = "idUsuario")
     @JsonIgnore
     @EqualsAndHashCode.Exclude
     private UsuarioEntity usuario;
 }
