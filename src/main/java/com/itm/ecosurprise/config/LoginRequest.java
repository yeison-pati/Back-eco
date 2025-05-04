package com.itm.ecosurprise.config;

import lombok.Data;

@Data
public class LoginRequest {
    private String correo;
    private String contrasena;
}
