package com.bank.clientes_cuentas_microservicio.application.service;

import com.bank.clientes_cuentas_microservicio.application.dto.ClienteDto;
import com.bank.clientes_cuentas_microservicio.application.mapper.ClienteMapper;
import com.bank.clientes_cuentas_microservicio.application.mapper.CuentaMapper;
import com.bank.clientes_cuentas_microservicio.application.port.out.ClienteRepositoryPort;
import com.bank.clientes_cuentas_microservicio.domain.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetClienteByDniServiceTest {

    @Mock
    private ClienteRepositoryPort clienteRepositoryPort;

    private final ClienteMapper clienteMapper = new ClienteMapper(new CuentaMapper());

    private GetClienteByDniService getClienteByDniService;

    @BeforeEach
    void setUp() {
        getClienteByDniService = new GetClienteByDniService(clienteRepositoryPort, clienteMapper);
    }

    @Test
    void execute_WhenClienteExists_ShouldReturnClienteDto() {
        String dni = "12345678A";
        Cliente cliente = Cliente.builder().dni(dni).build();

        when(clienteRepositoryPort.findByDni(dni)).thenReturn(Optional.of(cliente));

        Optional<ClienteDto> result = getClienteByDniService.execute(dni);

        assertTrue(result.isPresent());
        assertEquals(dni, result.get().getDni());
    }

    @Test
    void execute_WhenClienteDoesNotExist_ShouldReturnEmpty() {
        String dni = "12345678A";
        when(clienteRepositoryPort.findByDni(dni)).thenReturn(Optional.empty());

        Optional<ClienteDto> result = getClienteByDniService.execute(dni);

        assertTrue(result.isEmpty());
    }
}
