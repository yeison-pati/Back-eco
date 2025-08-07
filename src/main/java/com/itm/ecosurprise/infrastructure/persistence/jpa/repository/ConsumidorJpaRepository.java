package com.itm.ecosurprise.infrastructure.persistence.jpa.repository;

import com.itm.ecosurprise.infrastructure.persistence.jpa.entity.ConsumidorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumidorJpaRepository extends JpaRepository<ConsumidorEntity, Integer> {

}
