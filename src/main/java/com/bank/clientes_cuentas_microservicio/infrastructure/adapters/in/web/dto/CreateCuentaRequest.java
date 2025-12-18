package com.bank.clientes_cuentas_microservicio.infrastructure.adapters.in.web.dto;

import com.bank.clientes_cuentas_microservicio.domain.model.TipoCuenta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CreateCuentaRequest {

    @NotBlank(message = "El DNI no puede estar vacío")
    @Pattern(regexp = "^[0-9]{8}[A-Z]$", message = "El formato del DNI debe ser 8 números seguidos de una letra mayúscula")
    private String dniCliente;

    @NotNull(message = "El tipo de cuenta es obligatorio")
    private TipoCuenta tipoCuenta;

    @NotNull(message = "El total es obligatorio")
    @PositiveOrZero(message = "El total debe ser positivo o cero")
    private Double total;
}
