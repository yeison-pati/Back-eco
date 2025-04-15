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
    private long idOrden;

    @OneToOne
    private Consumidor consumidor;

    @OneToOne
    private Fecha fechaOrden;

    private double montoTotal;

    @OneToOne
    private Direccion direccionEntrega;

    @OneToMany
    private List<OrdenProducto> productos;

    @Enumerated(EnumType.STRING)
    private EstadoOrden estadoOrden;

    @OneToOne
    private Pago pago;
}

