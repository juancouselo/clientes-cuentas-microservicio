package com.bank.clientes_cuentas_microservicio.infrastructure.adapters.in.web;

import com.bank.clientes_cuentas_microservicio.domain.model.TipoCuenta;
import com.bank.clientes_cuentas_microservicio.infrastructure.adapters.in.web.dto.CreateCuentaRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CuentasControllerValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createAccount_WithValidData_ShouldReturnCreated() throws Exception {
        CreateCuentaRequest request = new CreateCuentaRequest();
        request.setDniCliente("12345678Z");
        request.setTipoCuenta(TipoCuenta.NORMAL);
        request.setTotal(1000.0);

        mockMvc.perform(post("/cuentas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void createAccount_WithEmptyDni_ShouldReturnBadRequest() throws Exception {
        CreateCuentaRequest request = new CreateCuentaRequest();
        request.setDniCliente("");
        request.setTipoCuenta(TipoCuenta.NORMAL);
        request.setTotal(1000.0);

        mockMvc.perform(post("/cuentas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createAccount_WithInvalidDniFormat_ShouldReturnBadRequest() throws Exception {
        CreateCuentaRequest request = new CreateCuentaRequest();
        request.setDniCliente("12345");
        request.setTipoCuenta(TipoCuenta.NORMAL);
        request.setTotal(1000.0);

        mockMvc.perform(post("/cuentas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createAccount_WithNullTipoCuenta_ShouldReturnBadRequest() throws Exception {
        CreateCuentaRequest request = new CreateCuentaRequest();
        request.setDniCliente("12345678Z");
        request.setTipoCuenta(null);
        request.setTotal(1000.0);

        mockMvc.perform(post("/cuentas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createAccount_WithNullTotal_ShouldReturnBadRequest() throws Exception {
        CreateCuentaRequest request = new CreateCuentaRequest();
        request.setDniCliente("12345678Z");
        request.setTipoCuenta(TipoCuenta.NORMAL);
        request.setTotal(null);

        mockMvc.perform(post("/cuentas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createAccount_WithNegativeTotal_ShouldReturnBadRequest() throws Exception {
        CreateCuentaRequest request = new CreateCuentaRequest();
        request.setDniCliente("12345678Z");
        request.setTipoCuenta(TipoCuenta.NORMAL);
        request.setTotal(-100.0);

        mockMvc.perform(post("/cuentas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
