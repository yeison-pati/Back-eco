package com.itm.ecosurprise.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.itm.ecosurprise.enums.Rol;


/*
 * @Data genera metodos y contructores
 * @Inheritance indica que es una clase padre y se usara herencia
 * para que cada subclase tenga su propia tabla, pero comparta el ID 
 * @Entity indica que es una entidad de la base de datos
 * @Table indica el nombre de la tabla en la base de datos
 */

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String nombre;
    private String correo;
    private String contrasena;

    @OneToMany
    private List<Telefono> celulares = new ArrayList<>();

    @OneToMany
    private List<UsuarioDireccion> direcciones = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Rol rol;

}

