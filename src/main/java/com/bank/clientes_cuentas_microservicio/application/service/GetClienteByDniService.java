package com.bank.clientes_cuentas_microservicio.application.service;

import com.bank.clientes_cuentas_microservicio.application.dto.ClienteDto;
import com.bank.clientes_cuentas_microservicio.application.mapper.ClienteMapper;
import com.bank.clientes_cuentas_microservicio.application.port.in.GetClienteByDniUseCase;
import com.bank.clientes_cuentas_microservicio.application.port.out.ClienteRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetClienteByDniService implements GetClienteByDniUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;
    private final ClienteMapper clienteMapper;

    @Override
    public Optional<ClienteDto> execute(String dni) {
        return clienteRepositoryPort.findByDni(dni).map(clienteMapper::toDto);
    }
}
