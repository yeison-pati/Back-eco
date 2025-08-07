package com.itm.ecosurprise.domain.port.in;

import com.itm.ecosurprise.domain.model.Usuario;

public interface SetUserImageUseCase {
    Usuario setImagen(int idUsuario, byte[] imagen, String fileName);
}
