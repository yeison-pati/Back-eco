package com.itm.ecosurprise.models;

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
    private long idDireccion;

    @Column(name = "pais", nullable = false)
    private String pais;

    @Column(name = "departamento", nullable = false)
    private String departamento;

    @Column( name = "calle", nullable = false)
    private String calle;

    @Column( name = "numero", nullable = false)
    private String numero;

    @Column( name = "complemento", nullable = false)
    private String complemento;
}
