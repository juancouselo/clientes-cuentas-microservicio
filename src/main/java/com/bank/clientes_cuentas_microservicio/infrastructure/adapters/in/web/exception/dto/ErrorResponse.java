package com.bank.clientes_cuentas_microservicio.infrastructure.adapters.in.web.exception.dto;

import java.util.Map;

public record ErrorResponse(
                int status,
                String message,
                Map<String, String> errors) {
}
