package com.bank.clientes_cuentas_microservicio.application.mapper;

import com.bank.clientes_cuentas_microservicio.application.dto.ClienteDto;
import com.bank.clientes_cuentas_microservicio.domain.model.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClienteMapper {

    private final CuentaMapper cuentaMapper;

    public ClienteDto toDto(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        return ClienteDto.builder()
                .dni(cliente.getDni())
                .nombre(cliente.getNombre())
                .apellido1(cliente.getApellido1())
                .apellido2(cliente.getApellido2())
                .fechaNacimiento(cliente.getFechaNacimiento())
                .cuentas(cliente.getCuentas() == null ? null
                        : cliente.getCuentas().stream()
                                .map(cuentaMapper::toDto)
                                .collect(Collectors.toList()))
                .build();
    }
}
