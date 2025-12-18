package com.bank.clientes_cuentas_microservicio.infrastructure.adapters.in.web.dto;

import com.bank.clientes_cuentas_microservicio.domain.model.TipoCuenta;
import lombok.Data;

@Data
public class CreateCuentaRequest {
    private String dniCliente;
    private TipoCuenta tipoCuenta;
    private Double total;
}
