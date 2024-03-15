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

import com.banquito.core.baking.cuenta.controller.CuentaController;
import com.banquito.core.baking.cuenta.dao.CuentaRepository;
import com.banquito.core.baking.cuenta.domain.Cuenta;
import com.banquito.core.baking.cuenta.service.CuentaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)

public class CuentaControllerTest {

    private MockMvc mockMvcCuenta;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectwrittWriter = objectMapper.writer();

    @Mock
    private CuentaRepository cuentaRepository;

    @InjectMocks
    private CuentaController cuentaController;

    Cuenta REGISTRO_1 = new Cuenta();

    @BeforeEach

    public void setUp() {
        MockitoAnnotations.initMocks(this);
        CuentaService cuentaService = new CuentaService(cuentaRepository);
        cuentaController = new CuentaController(cuentaService);
        this.mockMvcCuenta = MockMvcBuilders.standaloneSetup(cuentaController).build();
    }

    @Test
    public void obtenerCuenta_success() throws Exception {
        mockMvcCuenta.perform(MockMvcRequestBuilders
                .get("/api/v1/cuentas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
}
