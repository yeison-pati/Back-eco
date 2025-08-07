package com.itm.ecosurprise.domain.port.in;

import java.util.Map;

public interface LoginUseCase {
    Map<String, Object> login(String correo, String contrasena);
}
