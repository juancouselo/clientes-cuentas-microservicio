package com.bank.clientes_cuentas_microservicio.infrastructure.adapters.out.persistence.repository;

import com.bank.clientes_cuentas_microservicio.infrastructure.adapters.out.persistence.entity.CuentaBancariaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaBancariaRepository extends JpaRepository<CuentaBancariaEntity, Long> {
}
