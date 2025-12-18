package com.bank.clientes_cuentas_microservicio.infrastructure.adapters.in.web.mapper;

import com.bank.clientes_cuentas_microservicio.application.port.in.CreateCuentaCommand;
import com.bank.clientes_cuentas_microservicio.infrastructure.adapters.in.web.dto.CreateCuentaRequest;
import org.springframework.stereotype.Component;

@Component
public class CuentaWebMapper {

    public CreateCuentaCommand toCommand(CreateCuentaRequest request) {
        if (request == null) {
            return null;
        }
        return CreateCuentaCommand.builder()
                .dniCliente(request.getDniCliente())
                .tipoCuenta(request.getTipoCuenta())
                .total(request.getTotal())
                .build();
    }
}
