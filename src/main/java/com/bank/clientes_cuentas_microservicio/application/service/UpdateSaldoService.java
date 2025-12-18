package com.bank.clientes_cuentas_microservicio.application.service;

import com.bank.clientes_cuentas_microservicio.application.dto.CuentaDto;
import com.bank.clientes_cuentas_microservicio.application.mapper.CuentaMapper;
import com.bank.clientes_cuentas_microservicio.application.port.in.UpdateSaldoUseCase;
import com.bank.clientes_cuentas_microservicio.application.port.out.CuentaRepositoryPort;
import com.bank.clientes_cuentas_microservicio.domain.model.CuentaBancaria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateSaldoService implements UpdateSaldoUseCase {

    private final CuentaRepositoryPort repository;
    private final CuentaMapper cuentaMapper;

    @Override
    public Optional<CuentaDto> execute(Long id, Double total) {
        return repository.findById(id).map(cuenta -> {
            cuenta.actualizarSaldo(total);
            CuentaBancaria updatedCuenta = repository.save(cuenta);
            return cuentaMapper.toDto(updatedCuenta);
        });
    }
}
