package com.bank.clientes_cuentas_microservicio.infrastructure.adapters.out.persistence;

import com.bank.clientes_cuentas_microservicio.application.port.out.CuentaRepositoryPort;
import com.bank.clientes_cuentas_microservicio.domain.model.CuentaBancaria;
import com.bank.clientes_cuentas_microservicio.infrastructure.adapters.out.persistence.entity.CuentaBancariaEntity;
import com.bank.clientes_cuentas_microservicio.infrastructure.adapters.out.persistence.mapper.CuentaBancariaEntityMapper;
import com.bank.clientes_cuentas_microservicio.infrastructure.adapters.out.persistence.repository.CuentaBancariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CuentaPersistenceAdapter implements CuentaRepositoryPort {

    private final CuentaBancariaRepository cuentaRepository;
    private final CuentaBancariaEntityMapper cuentaMapper;

    @Override
    public CuentaBancaria save(CuentaBancaria cuenta) {
        CuentaBancariaEntity entity = cuentaMapper.toEntity(cuenta);
        CuentaBancariaEntity saved = cuentaRepository.save(entity);
        return cuentaMapper.toDomain(saved);
    }

    @Override
    public Optional<CuentaBancaria> findById(Long id) {
        return cuentaRepository.findById(id)
                .map(cuentaMapper::toDomain);
    }
}
