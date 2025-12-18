package com.bank.clientes_cuentas_microservicio.infrastructure.adapters.in.web;

import com.bank.clientes_cuentas_microservicio.domain.model.TipoCuenta;
import com.bank.clientes_cuentas_microservicio.infrastructure.adapters.in.web.dto.CreateCuentaRequest;
import com.bank.clientes_cuentas_microservicio.infrastructure.adapters.in.web.dto.UpdateCuentaRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CuentasControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createAccount_ForExistingClient_ShouldCreateAccount() throws Exception {
        CreateCuentaRequest request = new CreateCuentaRequest();
        request.setDniCliente("11111111A");
        request.setTipoCuenta(TipoCuenta.NORMAL);
        request.setTotal(1000.0);

        mockMvc.perform(post("/cuentas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.dniCliente", is("11111111A")))
                .andExpect(jsonPath("$.total", is(1000.0)))
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    @Test
    void createAccount_ForNewClient_ShouldCreateClientAndAccount() throws Exception {
        String newDni = "99999999Z";
        CreateCuentaRequest request = new CreateCuentaRequest();
        request.setDniCliente(newDni);
        request.setTipoCuenta(TipoCuenta.PREMIUM);
        request.setTotal(5000.0);

        mockMvc.perform(post("/cuentas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.dniCliente", is(newDni)));

        mockMvc.perform(get("/clientes/{dni}", newDni))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dni", is(newDni)));
    }

    @Test
    void updateAccountBalance_ShouldUpdateBalance() throws Exception {
        Long accountId = 1L;
        Double newBalance = 250000.0;

        UpdateCuentaRequest request = new UpdateCuentaRequest();
        request.setTotal(newBalance);

        mockMvc.perform(put("/cuentas/{idCuenta}", accountId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(accountId.intValue())))
                .andExpect(jsonPath("$.total", is(newBalance)));
    }
}
