package com.itm.ecosurprise.infrastructure.persistence.jpa.repository;

import com.itm.ecosurprise.infrastructure.persistence.jpa.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Integer> {

    @Query("SELECT u FROM UsuarioEntity u WHERE u.correo = :correo")
    Optional<UsuarioEntity> findByCorreo(String correo);

    @Query("SELECT u FROM UsuarioEntity u WHERE u.telefono.numero = :numero")
    Optional<UsuarioEntity> findByNumero(String numero);
}
