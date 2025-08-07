package com.itm.ecosurprise.infrastructure.persistence.jpa.repository;

import com.itm.ecosurprise.infrastructure.persistence.jpa.entity.RepartidorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepartidorJpaRepository extends JpaRepository<RepartidorEntity, Integer> {

}
