package com.bank.clientes_cuentas_microservicio.application.port.in;

import com.bank.clientes_cuentas_microservicio.application.dto.CuentaDto;
import java.util.Optional;

public interface UpdateSaldoUseCase {
    Optional<CuentaDto> execute(Long id, Double total);
}
