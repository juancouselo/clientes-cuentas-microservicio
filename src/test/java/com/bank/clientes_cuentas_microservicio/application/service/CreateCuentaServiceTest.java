package com.bank.clientes_cuentas_microservicio.application.service;

import com.bank.clientes_cuentas_microservicio.application.dto.CuentaDto;
import com.bank.clientes_cuentas_microservicio.application.mapper.CuentaMapper;
import com.bank.clientes_cuentas_microservicio.application.port.in.CreateCuentaCommand;
import com.bank.clientes_cuentas_microservicio.application.port.out.ClienteRepositoryPort;
import com.bank.clientes_cuentas_microservicio.application.port.out.CuentaRepositoryPort;
import com.bank.clientes_cuentas_microservicio.domain.model.Cliente;
import com.bank.clientes_cuentas_microservicio.domain.model.CuentaBancaria;
import com.bank.clientes_cuentas_microservicio.domain.model.TipoCuenta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCuentaServiceTest {

    @Mock
    private ClienteRepositoryPort clienteRepository;

    @Mock
    private CuentaRepositoryPort cuentaRepository;

    private final CuentaMapper cuentaMapper = new CuentaMapper();

    private CreateCuentaService createCuentaService;

    private CuentaBancaria cuenta;
    private CreateCuentaCommand command;

    @BeforeEach
    void setUp() {
        createCuentaService = new CreateCuentaService(clienteRepository, cuentaRepository, cuentaMapper);
        
        command = CreateCuentaCommand.builder()
                .dniCliente("12345678A")
                .tipoCuenta(TipoCuenta.NORMAL)
                .total(1000.0)
                .build();

        cuenta = CuentaBancaria.builder()
                .dniCliente("12345678A")
                .tipoCuenta(TipoCuenta.NORMAL)
                .total(1000.0)
                .build();
    }

    @Test
    void execute_WhenClienteExists_ShouldOnlySaveCuenta() {
        when(clienteRepository.findByDni(command.getDniCliente())).thenReturn(Optional.of(new Cliente()));
        when(cuentaRepository.save(any(CuentaBancaria.class))).thenReturn(cuenta);

        CuentaDto result = createCuentaService.execute(command);

        assertNotNull(result);
        assertEquals(command.getDniCliente(), result.getDniCliente());
        verify(clienteRepository, never()).saveCliente(any(Cliente.class));
        verify(cuentaRepository, times(1)).save(any(CuentaBancaria.class));
    }

    @Test
    void execute_WhenClienteDoesNotExist_ShouldSaveClienteAndCuenta() {
        when(clienteRepository.findByDni(command.getDniCliente())).thenReturn(Optional.empty());
        when(cuentaRepository.save(any(CuentaBancaria.class))).thenReturn(cuenta);

        CuentaDto result = createCuentaService.execute(command);

        assertNotNull(result);
        verify(clienteRepository, times(1)).saveCliente(any(Cliente.class));
        verify(cuentaRepository, times(1)).save(any(CuentaBancaria.class));
    }
}
