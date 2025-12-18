package com.bank.clientes_cuentas_microservicio.application.mapper;

import com.bank.clientes_cuentas_microservicio.application.dto.CuentaDto;
import com.bank.clientes_cuentas_microservicio.application.port.in.CreateCuentaCommand;
import com.bank.clientes_cuentas_microservicio.domain.model.CuentaBancaria;
import org.springframework.stereotype.Component;

@Component
public class CuentaMapper {

    public CuentaDto toDto(CuentaBancaria cuenta) {
        if (cuenta == null) {
            return null;
        }
        return CuentaDto.builder()
                .id(cuenta.getId())
                .dniCliente(cuenta.getDniCliente())
                .tipoCuenta(cuenta.getTipoCuenta())
                .total(cuenta.getTotal())
                .build();
    }

    public CuentaBancaria toDomain(CreateCuentaCommand command) {
        if (command == null) {
            return null;
        }
        return CuentaBancaria.builder()
                .dniCliente(command.getDniCliente())
                .tipoCuenta(command.getTipoCuenta())
                .total(command.getTotal())
                .build();
    }
}
