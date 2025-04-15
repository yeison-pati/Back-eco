package com.itm.ecosurprise.models;

import com.itm.ecosurprise.enums.EstadoPago;
import com.itm.ecosurprise.enums.MetodoPago;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPago;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    private Fecha fechaPago;

    @Enumerated(EnumType.STRING)
    private EstadoPago estadoPago;

    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    private float montoPagado;
}

