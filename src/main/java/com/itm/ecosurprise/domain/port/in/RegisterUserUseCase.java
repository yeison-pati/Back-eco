package com.itm.ecosurprise.domain.port.in;

import com.itm.ecosurprise.domain.model.Usuario;
import java.util.Map;

public interface RegisterUserUseCase {
    Usuario register(Map<String, Object> userData);
}
