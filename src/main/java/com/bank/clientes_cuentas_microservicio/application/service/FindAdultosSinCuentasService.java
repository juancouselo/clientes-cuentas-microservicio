package com.bank.clientes_cuentas_microservicio.application.service;

import com.bank.clientes_cuentas_microservicio.application.dto.ClienteDto;
import com.bank.clientes_cuentas_microservicio.application.mapper.ClienteMapper;
import com.bank.clientes_cuentas_microservicio.application.port.in.FindAdultosSinCuentasUseCase;
import com.bank.clientes_cuentas_microservicio.application.port.out.ClienteRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindAdultosSinCuentasService implements FindAdultosSinCuentasUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;
    private final ClienteMapper clienteMapper;

    @Override
    public List<ClienteDto> execute() {
        return clienteRepositoryPort.findAdultosSinCuentas().stream()
                .map(clienteMapper::toDto)
                .collect(Collectors.toList());
    }
}
