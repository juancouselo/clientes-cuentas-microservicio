package com.bank.clientes_cuentas_microservicio.infrastructure.adapters.out.persistence.mapper;

import com.bank.clientes_cuentas_microservicio.domain.model.Cliente;
import com.bank.clientes_cuentas_microservicio.domain.model.CuentaBancaria;
import com.bank.clientes_cuentas_microservicio.infrastructure.adapters.out.persistence.entity.ClienteEntity;
import com.bank.clientes_cuentas_microservicio.infrastructure.adapters.out.persistence.entity.CuentaBancariaEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteEntityMapper {

    public Cliente toDomain(ClienteEntity entity) {
        if (entity == null) {
            return null;
        }
        return Cliente.builder()
                .dni(entity.getDni())
                .nombre(entity.getNombre())
                .apellido1(entity.getApellido1())
                .apellido2(entity.getApellido2())
                .fechaNacimiento(entity.getFechaNacimiento())
                .cuentas(toDomainCuentas(entity.getCuentas()))
                .build();
    }

    public ClienteEntity toEntity(Cliente domain) {
        if (domain == null) {
            return null;
        }
        ClienteEntity entity = new ClienteEntity();
        entity.setDni(domain.getDni());
        entity.setNombre(domain.getNombre());
        entity.setApellido1(domain.getApellido1());
        entity.setApellido2(domain.getApellido2());
        entity.setFechaNacimiento(domain.getFechaNacimiento());

        if (domain.getCuentas() != null) {
            entity.setCuentas(domain.getCuentas().stream()
                    .map(c -> toEntityCuenta(c, entity))
                    .collect(Collectors.toList()));
        }
        return entity;
    }

    private List<CuentaBancaria> toDomainCuentas(List<CuentaBancariaEntity> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(this::toDomainCuenta)
                .collect(Collectors.toList());
    }

    private CuentaBancaria toDomainCuenta(CuentaBancariaEntity entity) {
        return CuentaBancaria.builder()
                .id(entity.getId())
                .dniCliente(entity.getCliente() != null ? entity.getCliente().getDni() : null)
                .tipoCuenta(entity.getTipoCuenta())
                .total(entity.getTotal())
                .build();
    }

    private CuentaBancariaEntity toEntityCuenta(CuentaBancaria domain, ClienteEntity clienteEntity) {
        CuentaBancariaEntity entity = new CuentaBancariaEntity();
        entity.setId(domain.getId());
        entity.setTipoCuenta(domain.getTipoCuenta());
        entity.setTotal(domain.getTotal());
        entity.setCliente(clienteEntity);
        return entity;
    }
}
