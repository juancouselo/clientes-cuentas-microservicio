package com.bank.clientes_cuentas_microservicio.application.service;

import com.bank.clientes_cuentas_microservicio.application.dto.CuentaDto;
import com.bank.clientes_cuentas_microservicio.application.mapper.CuentaMapper;
import com.bank.clientes_cuentas_microservicio.application.port.in.CreateCuentaCommand;
import com.bank.clientes_cuentas_microservicio.application.port.in.CreateCuentaUseCase;
import com.bank.clientes_cuentas_microservicio.application.port.out.ClienteRepositoryPort;
import com.bank.clientes_cuentas_microservicio.application.port.out.CuentaRepositoryPort;
import com.bank.clientes_cuentas_microservicio.domain.model.Cliente;
import com.bank.clientes_cuentas_microservicio.domain.model.CuentaBancaria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateCuentaService implements CreateCuentaUseCase {

    private final ClienteRepositoryPort clienteRepository;
    private final CuentaRepositoryPort cuentaRepository;
    private final CuentaMapper cuentaMapper;

    @Override
    public CuentaDto execute(CreateCuentaCommand command) {
        CuentaBancaria cuenta = cuentaMapper.toDomain(command);

        Optional<Cliente> existingCliente = clienteRepository.findByDni(cuenta.getDniCliente());

        if (existingCliente.isEmpty()) {
            Cliente newCliente = Cliente.builder()
                    .dni(cuenta.getDniCliente())
                    .build();
            clienteRepository.saveCliente(newCliente);
        }

        CuentaBancaria savedCuenta = cuentaRepository.save(cuenta);
        return cuentaMapper.toDto(savedCuenta);
    }
}
