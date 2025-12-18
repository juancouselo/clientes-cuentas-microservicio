package com.bank.clientes_cuentas_microservicio.infrastructure.adapters.out.persistence;

import com.bank.clientes_cuentas_microservicio.application.port.out.ClienteRepositoryPort;
import com.bank.clientes_cuentas_microservicio.domain.model.Cliente;
import com.bank.clientes_cuentas_microservicio.infrastructure.adapters.out.persistence.entity.ClienteEntity;
import com.bank.clientes_cuentas_microservicio.infrastructure.adapters.out.persistence.mapper.ClienteEntityMapper;
import com.bank.clientes_cuentas_microservicio.infrastructure.adapters.out.persistence.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClientePersistenceAdapter implements ClienteRepositoryPort {

    private final ClienteRepository clienteRepository;
    private final ClienteEntityMapper clienteMapper;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll().stream()
                .map(clienteMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cliente> findAdultosSinCuentas() {
        LocalDate fechaLimite = LocalDate.now().minusYears(18);
        return clienteRepository.findAdultosSinCuentas(fechaLimite).stream()
                .map(clienteMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cliente> findBySaldoMayorQue(Double cantidad) {
        return clienteRepository.findBySaldoMayorQue(cantidad).stream()
                .map(clienteMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cliente> findByDni(String dni) {
        return clienteRepository.findById(dni)
                .map(clienteMapper::toDomain);
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        ClienteEntity entity = clienteMapper.toEntity(cliente);
        ClienteEntity savedEntity = clienteRepository.save(entity);
        return clienteMapper.toDomain(savedEntity);
    }
}
