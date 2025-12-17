package com.bank.clientes_cuentas_microservicio.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuentaBancaria {

    private Long id;
    private String dniCliente;
    private TipoCuenta tipoCuenta;
    private Double total;

    public void actualizarSaldo(Double nuevoSaldo) {
        if (nuevoSaldo == null)
            throw new IllegalArgumentException("nuevoSaldo no puede ser null");
        this.total = nuevoSaldo;
    }
}