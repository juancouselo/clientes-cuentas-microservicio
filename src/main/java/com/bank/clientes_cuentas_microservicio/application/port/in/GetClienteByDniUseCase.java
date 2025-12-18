package com.bank.clientes_cuentas_microservicio.application.port.in;

import com.bank.clientes_cuentas_microservicio.application.dto.ClienteDto;
import java.util.Optional;

public interface GetClienteByDniUseCase {
    Optional<ClienteDto> execute(String dni);
}
