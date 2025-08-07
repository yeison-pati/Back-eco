package com.itm.ecosurprise.infrastructure.persistence.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "consumidores")
public class ConsumidorEntity extends UsuarioEntity {

    private int puntos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value ={
        "idUsuarioDireccion","usuario"
    })
    private List<UsuarioDireccionEntity> direcciones = new ArrayList<>();

    @OneToMany(mappedBy = "consumidor", cascade = CascadeType.ALL)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private List<OrdenEntity> ordenes = new ArrayList<>();
}
