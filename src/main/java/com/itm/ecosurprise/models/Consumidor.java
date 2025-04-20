package com.itm.ecosurprise.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/*
 * @EqualsAndHashCode(callSuper = true) permite usar el constructor de la clase padre
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "consumidores")
public class Consumidor extends Usuario {

    private int puntos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<UsuarioDireccion> direcciones = new ArrayList<>();

    @OneToMany(mappedBy = "consumidor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Orden> ordenes = new ArrayList<>();
}
