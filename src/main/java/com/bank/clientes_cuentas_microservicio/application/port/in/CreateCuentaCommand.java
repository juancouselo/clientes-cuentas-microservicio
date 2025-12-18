package com.bank.clientes_cuentas_microservicio.application.port.in;

import com.bank.clientes_cuentas_microservicio.domain.model.TipoCuenta;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCuentaCommand {
    private String dniCliente;
    private TipoCuenta tipoCuenta;
    private Double total;
}
