package com.itm.ecosurprise.models;

import com.itm.ecosurprise.enums.EstadoOrden;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrden;

    @OneToOne
    private Consumidor consumidor;

    @OneToOne
    private Fecha fechaOrden;

    private float montoTotal;

    @OneToOne
    private Direccion direccionEntrega;

    @OneToMany
    private List<OrdenProducto> productos;

    private String estadoOrden;

    @OneToOne
    private Pago pago;
}

