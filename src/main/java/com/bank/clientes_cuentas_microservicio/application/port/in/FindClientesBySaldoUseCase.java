package com.bank.clientes_cuentas_microservicio.application.port.in;

import com.bank.clientes_cuentas_microservicio.application.dto.ClienteDto;
import java.util.List;

public interface FindClientesBySaldoUseCase {
    List<ClienteDto> execute(Double cantidad);
}
