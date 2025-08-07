package com.itm.ecosurprise.infrastructure.persistence.jpa.repository;

import com.itm.ecosurprise.infrastructure.persistence.jpa.entity.OrdenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenJpaRepository extends JpaRepository<OrdenEntity, Integer> {
    @Query("SELECT DISTINCT o FROM OrdenEntity o JOIN o.productos op WHERE op.producto.comerciante.idUsuario = :idComerciante")
    List<OrdenEntity> findAllByIdComerciante(int idComerciante);

    @Query("SELECT o FROM OrdenEntity o JOIN o.productos op WHERE o.idOrden = :idOrden AND op.producto.comerciante.idUsuario = :idComerciante")
    OrdenEntity findByIdAndComerciante(int idOrden, int idComerciante);
}
