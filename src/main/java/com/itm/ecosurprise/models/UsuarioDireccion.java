package com.itm.ecosurprise.models;

import jakarta.persistence.*;
import lombok.Data;


/*
 * @Data genera metodos y contructores
 * @Entity indica que es una entidad de la base de datos
 * @Table indica el nombre de la tabla en la base de datos
 * @Column indica el nombre de la columna en la base de datos y si es nullable o unique
 */

@Data
@Entity
@Table(name = "usuarioDireccion")
public class UsuarioDireccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuarioDireccion;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Direccion direccion;

}
