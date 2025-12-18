package com.bank.clientes_cuentas_microservicio.infrastructure.adapters.in.web;

import com.bank.clientes_cuentas_microservicio.application.dto.CuentaDto;
import com.bank.clientes_cuentas_microservicio.application.port.in.CreateCuentaCommand;
import com.bank.clientes_cuentas_microservicio.application.port.in.CreateCuentaUseCase;
import com.bank.clientes_cuentas_microservicio.application.port.in.UpdateSaldoUseCase;
import com.bank.clientes_cuentas_microservicio.infrastructure.adapters.in.web.dto.CreateCuentaRequest;
import com.bank.clientes_cuentas_microservicio.infrastructure.adapters.in.web.dto.UpdateCuentaRequest;
import com.bank.clientes_cuentas_microservicio.infrastructure.adapters.in.web.mapper.CuentaWebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CreateCuentaUseCase createCuentaUseCase;
    private final UpdateSaldoUseCase updateSaldoUseCase;
    private final CuentaWebMapper cuentaWebMapper;

    @PostMapping
    public ResponseEntity<CuentaDto> createAccount(@RequestBody @Valid CreateCuentaRequest request) {
        CreateCuentaCommand command = cuentaWebMapper.toCommand(request);
        
        CuentaDto created = createCuentaUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{idCuenta}")
    public ResponseEntity<CuentaDto> updateAccountBalance(@PathVariable Long idCuenta, @RequestBody UpdateCuentaRequest request) {
        return updateSaldoUseCase.execute(idCuenta, request.getTotal())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
