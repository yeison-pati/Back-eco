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
    private int idPago;

    @OneToOne
    private Fecha fechaPago;
    private String estadoPago;
    private String metodoPago;
    private float montoPagado;
}

