package com.itm.ecosurprise.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;
/*
 * @Data genera metodos y contructores
 * @Entity indica que es una entidad de la base de datos
 * @Table indica el nombre de la tabla en la base de datos
 * @Column indica el nombre de la columna en la base de datos y si es nullable o unique
 * nullable indica la posibilidad de que el campo sea nulo
 * 
 */
@Data
@Entity
@Table(name = "direcciones")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDireccion")
    private int idDireccion;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    @JsonBackReference // Usuario tiene la referencia gestionada
    private Usuario usuario;

    private String departamento;
    private String calle;
    private String numero;
    private String complemento;
}
