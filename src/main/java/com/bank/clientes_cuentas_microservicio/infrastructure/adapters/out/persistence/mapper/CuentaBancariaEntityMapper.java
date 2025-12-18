package com.bank.clientes_cuentas_microservicio.infrastructure.adapters.out.persistence.mapper;

import com.bank.clientes_cuentas_microservicio.domain.model.CuentaBancaria;
import com.bank.clientes_cuentas_microservicio.infrastructure.adapters.out.persistence.entity.ClienteEntity;
import com.bank.clientes_cuentas_microservicio.infrastructure.adapters.out.persistence.entity.CuentaBancariaEntity;
import org.springframework.stereotype.Component;

@Component
public class CuentaBancariaEntityMapper {

    public CuentaBancaria toDomain(CuentaBancariaEntity entity) {
        if (entity == null) {
            return null;
        }
        return CuentaBancaria.builder()
                .id(entity.getId())
                .dniCliente(entity.getCliente() != null ? entity.getCliente().getDni() : null)
                .tipoCuenta(entity.getTipoCuenta())
                .total(entity.getTotal())
                .build();
    }

    public CuentaBancariaEntity toEntity(CuentaBancaria domain) {
        if (domain == null) {
            return null;
        }
        CuentaBancariaEntity entity = new CuentaBancariaEntity();
        entity.setId(domain.getId());
        entity.setTipoCuenta(domain.getTipoCuenta());
        entity.setTotal(domain.getTotal());
        
        if (domain.getDniCliente() != null) {
             ClienteEntity cliente = new ClienteEntity();
             cliente.setDni(domain.getDniCliente());
             entity.setCliente(cliente);
        }
        return entity;
    }
}
