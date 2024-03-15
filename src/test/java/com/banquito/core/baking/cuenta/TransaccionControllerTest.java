package com.banquito.core.baking.cuenta;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.banquito.core.baking.cuenta.controller.TransaccionController;
import com.banquito.core.baking.cuenta.dao.CuentaRepository;
import com.banquito.core.baking.cuenta.dao.TransaccionRepository;
import com.banquito.core.baking.cuenta.domain.Transaccion;
import com.banquito.core.baking.cuenta.service.TransaccionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)

public class TransaccionControllerTest {

    private MockMvc mockMvcTransaccion;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectwrittWriter = objectMapper.writer();

    @Mock
    private TransaccionRepository transaccionRepository;
    private CuentaRepository cuentaRepository;

    @InjectMocks
    private TransaccionController transaccionController;

    Transaccion REGISTRO_1 = new Transaccion(1);

    @BeforeEach

    public void setUp() {
        MockitoAnnotations.initMocks(this);
        TransaccionService transaccionService = new TransaccionService(transaccionRepository,cuentaRepository);
        transaccionController = new TransaccionController(transaccionService);
        this.mockMvcTransaccion = MockMvcBuilders.standaloneSetup(transaccionController).build();
    }

    @Test
    public void obtenerTransacciones_success() throws Exception {
        mockMvcTransaccion.perform(MockMvcRequestBuilders
                .get("/api/v1/transacciones/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

}
