package com.bank.clientes_cuentas_microservicio.application.port.in;

import com.bank.clientes_cuentas_microservicio.application.dto.ClienteDto;
import java.util.List;

public interface FindAdultosSinCuentasUseCase {
    List<ClienteDto> execute();
}
