package com.bank.clientes_cuentas_microservicio.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.bank.clientes_cuentas_microservicio.domain.model.TipoCuenta;

@Data
@Entity
@Table(name = "cuentas_bancarias")
public class CuentaBancariaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoCuenta tipoCuenta;
    private Double total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dni_cliente")
    private ClienteEntity cliente;
}
