package com.itm.ecosurprise.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.itm.ecosurprise.models.Consumidor;
import com.itm.ecosurprise.models.Usuario;

@Repository
public interface IUsuario extends JpaRepository<Usuario, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario

    @Query("SELECT c FROM Consumidor c WHERE c.correo = :correo")
    Optional<Consumidor> findByCorreoJPQL(@PathVariable("correo") String correo);
}
