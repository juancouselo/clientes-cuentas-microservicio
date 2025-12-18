package com.bank.clientes_cuentas_microservicio.application.port.out;

import com.bank.clientes_cuentas_microservicio.domain.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteRepositoryPort {
    List<Cliente> findAll();

    List<Cliente> findAdultosSinCuentas();

    List<Cliente> findBySaldoMayorQue(Double cantidad);

    Optional<Cliente> findByDni(String dni);

    Cliente saveCliente(Cliente cliente);
}
