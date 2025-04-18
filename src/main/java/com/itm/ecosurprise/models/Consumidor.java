package com.itm.ecosurprise.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/*
 * @EqualsAndHashCode(callSuper = true) permite usar el constructor de la clase padre
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "consumidores")
public class Consumidor extends Usuario {
    private int puntos;

    @OneToMany
    @JsonManagedReference
    private List<UsuarioDireccion> direcciones = new ArrayList<>();

}
