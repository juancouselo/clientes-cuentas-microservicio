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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindClientesBySaldoServiceTest {

    @Mock
    private ClienteRepositoryPort clienteRepositoryPort;

    private final ClienteMapper clienteMapper = new ClienteMapper(new CuentaMapper());

    private FindClientesBySaldoService findClientesBySaldoService;

    @BeforeEach
    void setUp() {
        findClientesBySaldoService = new FindClientesBySaldoService(clienteRepositoryPort, clienteMapper);
    }

    @Test
    void execute_ShouldReturnListOfClienteDto() {
        Double cantidad = 500.0;
        Cliente cliente = Cliente.builder().dni("12345678A").build();

        when(clienteRepositoryPort.findBySaldoMayorQue(cantidad)).thenReturn(List.of(cliente));

        List<ClienteDto> result = findClientesBySaldoService.execute(cantidad);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("12345678A", result.get(0).getDni());
    }
}
