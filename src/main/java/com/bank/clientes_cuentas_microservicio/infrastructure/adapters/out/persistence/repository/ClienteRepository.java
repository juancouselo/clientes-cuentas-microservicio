package com.bank.clientes_cuentas_microservicio.infrastructure.adapters.out.persistence.repository;

import com.bank.clientes_cuentas_microservicio.infrastructure.adapters.out.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {

    @Query("SELECT c FROM ClienteEntity c WHERE c.cuentas IS EMPTY AND c.fechaNacimiento <= :fechaLimite")
    List<ClienteEntity> findAdultosSinCuentas(@Param("fechaLimite") LocalDate fechaLimite);

    @Query("SELECT DISTINCT c FROM ClienteEntity c JOIN c.cuentas cu WHERE cu.total > :saldo")
    List<ClienteEntity> findBySaldoMayorQue(@Param("saldo") Double saldo);
}
