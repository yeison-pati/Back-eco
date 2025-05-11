package com.itm.ecosurprise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itm.ecosurprise.models.Usuario;
import java.util.Optional;

@Repository
public interface IUsuario extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreo(String correo);

    // Aquí puedes agregar métodos personalizados si es necesario

}
