package com.bank.clientes_cuentas_microservicio.application.port.in;

import com.bank.clientes_cuentas_microservicio.application.dto.CuentaDto;

public interface CreateCuentaUseCase {
    CuentaDto execute(CreateCuentaCommand command);
}
