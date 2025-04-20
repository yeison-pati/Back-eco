package com.itm.ecosurprise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itm.ecosurprise.models.Usuario;

@Repository
public interface IUsuario extends JpaRepository<Usuario, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario

}
