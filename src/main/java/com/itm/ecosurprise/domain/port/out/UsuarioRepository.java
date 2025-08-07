package com.itm.ecosurprise.domain.port.out;

import com.itm.ecosurprise.domain.model.Usuario;

import java.util.Optional;
import java.util.List;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(Integer id);
    Optional<Usuario> findByCorreo(String correo);
    Optional<Usuario> findByNumero(String numero);
    List<Usuario> findAll();
    void deleteById(Integer id);
}
