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
class FindAllClientesServiceTest {

    @Mock
    private ClienteRepositoryPort clienteRepositoryPort;

    private final ClienteMapper clienteMapper = new ClienteMapper(new CuentaMapper());

    private FindAllClientesService findAllClientesService;

    @BeforeEach
    void setUp() {
        findAllClientesService = new FindAllClientesService(clienteRepositoryPort, clienteMapper);
    }

    @Test
    void execute_ShouldReturnListOfClienteDto() {
        Cliente cliente = Cliente.builder().dni("12345678A").build();

        when(clienteRepositoryPort.findAll()).thenReturn(List.of(cliente));

        List<ClienteDto> result = findAllClientesService.execute();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("12345678A", result.get(0).getDni());
    }
}
