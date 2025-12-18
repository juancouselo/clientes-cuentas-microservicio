package com.bank.clientes_cuentas_microservicio.application.port.in;

import com.bank.clientes_cuentas_microservicio.application.dto.CuentaDto;
import com.bank.clientes_cuentas_microservicio.domain.model.CuentaBancaria;

public interface CreateCuentaUseCase {
    CuentaDto execute(CuentaBancaria cuenta);
}
