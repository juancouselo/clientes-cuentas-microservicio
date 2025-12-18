package com.bank.clientes_cuentas_microservicio.application.service;

import com.bank.clientes_cuentas_microservicio.application.dto.CuentaDto;
import com.bank.clientes_cuentas_microservicio.application.mapper.CuentaMapper;
import com.bank.clientes_cuentas_microservicio.application.port.out.CuentaRepositoryPort;
import com.bank.clientes_cuentas_microservicio.domain.model.CuentaBancaria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateSaldoServiceTest {

    @Mock
    private CuentaRepositoryPort repository;

    private final CuentaMapper cuentaMapper = new CuentaMapper();

    private UpdateSaldoService updateSaldoService;

    @BeforeEach
    void setUp() {
        updateSaldoService = new UpdateSaldoService(repository, cuentaMapper);
    }

    @Test
    void execute_WhenCuentaExists_ShouldUpdateSaldoAndReturnDto() {
        Long id = 1L;
        Double newSaldo = 2000.0;
        CuentaBancaria cuenta = CuentaBancaria.builder().id(id).total(1000.0).build();

        when(repository.findById(id)).thenReturn(Optional.of(cuenta));
        when(repository.save(any(CuentaBancaria.class))).thenReturn(cuenta);

        Optional<CuentaDto> result = updateSaldoService.execute(id, newSaldo);

        assertTrue(result.isPresent());
        assertEquals(newSaldo, result.get().getTotal());
        verify(repository, times(1)).save(cuenta);
        assertEquals(newSaldo, cuenta.getTotal());
    }

    @Test
    void execute_WhenCuentaDoesNotExist_ShouldReturnEmpty() {
        Long id = 1L;
        Double newSaldo = 2000.0;
        when(repository.findById(id)).thenReturn(Optional.empty());

        Optional<CuentaDto> result = updateSaldoService.execute(id, newSaldo);

        assertTrue(result.isEmpty());
        verify(repository, never()).save(any());
    }
}
