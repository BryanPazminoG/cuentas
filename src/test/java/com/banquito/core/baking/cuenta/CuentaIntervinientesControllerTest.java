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

import com.banquito.core.baking.cuenta.controller.CuentaIntervinientesController;
import com.banquito.core.baking.cuenta.dao.CuentaIntervinientesRepository;
import com.banquito.core.baking.cuenta.domain.Cuenta;
import com.banquito.core.baking.cuenta.service.CuentaIntervinientesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)

public class CuentaIntervinientesControllerTest {

    private MockMvc mockMvcCuentaIntervinientes;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectwrittWriter = objectMapper.writer();

    @Mock
    private CuentaIntervinientesRepository cuentaIntervinientesRepository;

    @InjectMocks
    private CuentaIntervinientesController cuentaIntervinientesController;

    Cuenta REGISTRO_1 = new Cuenta();

    @BeforeEach

    public void setUp() {
        MockitoAnnotations.initMocks(this);
        CuentaIntervinientesService cuentaIntervinienteService = new CuentaIntervinientesService(cuentaIntervinientesRepository);
        cuentaIntervinientesController = new CuentaIntervinientesController(cuentaIntervinienteService);
        this.mockMvcCuentaIntervinientes = MockMvcBuilders.standaloneSetup(cuentaIntervinientesController).build();
    }

    @Test
    public void obtenerCuentaIntervinientes_success() throws Exception {
        mockMvcCuentaIntervinientes.perform(MockMvcRequestBuilders
                .get("/api/v1/cuentaintervinientes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

}
