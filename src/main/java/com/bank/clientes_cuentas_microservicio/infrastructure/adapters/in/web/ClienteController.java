package com.bank.clientes_cuentas_microservicio.infrastructure.adapters.in.web;

import com.bank.clientes_cuentas_microservicio.application.dto.ClienteDto;
import com.bank.clientes_cuentas_microservicio.application.port.in.FindAdultosSinCuentasUseCase;
import com.bank.clientes_cuentas_microservicio.application.port.in.FindAllClientesUseCase;
import com.bank.clientes_cuentas_microservicio.application.port.in.FindClientesBySaldoUseCase;
import com.bank.clientes_cuentas_microservicio.application.port.in.GetClienteByDniUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final FindAllClientesUseCase findAllClientesUseCase;
    private final FindAdultosSinCuentasUseCase findAdultosSinCuentasUseCase;
    private final FindClientesBySaldoUseCase findClientesBySaldoUseCase;
    private final GetClienteByDniUseCase getClienteByDniUseCase;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getAllClients() {
        return ResponseEntity.ok(findAllClientesUseCase.execute());
    }

    @GetMapping("/mayores-de-edad")
    public ResponseEntity<List<ClienteDto>> getAdultClients() {
        return ResponseEntity.ok(findAdultosSinCuentasUseCase.execute());
    }

    @GetMapping("/con-cuenta-superior-a/{cantidad}")
    public ResponseEntity<List<ClienteDto>> getClientsWithBalanceOver(@PathVariable Double cantidad) {
        return ResponseEntity.ok(findClientesBySaldoUseCase.execute(cantidad));
    }

    @GetMapping("/{dni}")
    public ResponseEntity<ClienteDto> getClientByDni(@PathVariable String dni) {
        return getClienteByDniUseCase.execute(dni)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
