package com.bank.clientes_cuentas_microservicio.application.mapper;

import com.bank.clientes_cuentas_microservicio.application.dto.CuentaDto;
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
}
