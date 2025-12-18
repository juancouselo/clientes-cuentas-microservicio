package com.bank.clientes_cuentas_microservicio.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "clientes")
public class ClienteEntity {

    @Id
    private String dni;

    private String nombre;
    private String apellido1;
    private String apellido2;
    private LocalDate fechaNacimiento;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CuentaBancariaEntity> cuentas;
}
