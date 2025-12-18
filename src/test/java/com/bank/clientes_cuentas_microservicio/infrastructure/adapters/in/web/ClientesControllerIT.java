package com.bank.clientes_cuentas_microservicio.infrastructure.adapters.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ClientesControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllClients_ShouldReturn5InitialClients() throws Exception {
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[*].dni", containsInAnyOrder(
                        "11111111A", "22222222B", "33333333C", "44444444D", "55555555E"
                )));
    }

    @Test
    void getAdultClients_ShouldReturnOnlyAdults() throws Exception {
        mockMvc.perform(get("/clientes/mayores-de-edad"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[*].dni", not(hasItem("33333333C"))));
    }

    @Test
    void getClientsWithBalanceOver_ShouldFilterCorrectly() throws Exception {
        mockMvc.perform(get("/clientes/con-cuenta-superior-a/{cantidad}", 100000.0))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].dni", containsInAnyOrder("11111111A", "55555555E")));
    }

    @Test
    void getClientByDni_ShouldReturnClient() throws Exception {
        String dni = "11111111A";
        mockMvc.perform(get("/clientes/{dni}", dni))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dni", is(dni)))
                .andExpect(jsonPath("$.nombre", is("Juan")));
    }
}
