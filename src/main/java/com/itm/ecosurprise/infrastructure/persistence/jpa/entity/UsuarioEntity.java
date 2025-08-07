package com.itm.ecosurprise.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuarios")
public abstract class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    private String imagen;
    private String nombre;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(nullable = false)
    private String contrasena;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private TelefonoEntity telefono;

    @Column(nullable = false)
    private String rol;
}
