package com.itm.ecosurprise.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reporte")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReporte;
    @OneToOne
    private Usuario Usuario;
    private String descripcion;
}

