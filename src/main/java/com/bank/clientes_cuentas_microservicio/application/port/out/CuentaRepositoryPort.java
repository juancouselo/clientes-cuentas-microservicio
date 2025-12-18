package com.bank.clientes_cuentas_microservicio.application.port.out;

import com.bank.clientes_cuentas_microservicio.domain.model.CuentaBancaria;
import java.util.Optional;

public interface CuentaRepositoryPort {
    CuentaBancaria save(CuentaBancaria cuenta);

    Optional<CuentaBancaria> findById(Long id);
}
