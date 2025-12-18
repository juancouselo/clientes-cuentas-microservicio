package com.bank.clientes_cuentas_microservicio.application.dto;

import com.bank.clientes_cuentas_microservicio.domain.model.TipoCuenta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuentaDto {
    private Long id;
    private String dniCliente;
    private TipoCuenta tipoCuenta;
    private Double total;
}
