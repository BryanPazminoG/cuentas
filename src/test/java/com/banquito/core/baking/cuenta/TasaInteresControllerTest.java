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
import com.banquito.core.baking.cuenta.controller.TasaInteresController;
import com.banquito.core.baking.cuenta.dao.CuentaIntervinientesRepository;
import com.banquito.core.baking.cuenta.dao.TasaInteresRepository;
import com.banquito.core.baking.cuenta.domain.Cuenta;
import com.banquito.core.baking.cuenta.domain.TasaInteres;
import com.banquito.core.baking.cuenta.service.CuentaIntervinientesService;
import com.banquito.core.baking.cuenta.service.TarjetaService;
import com.banquito.core.baking.cuenta.service.TasaInteresService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)

public class TasaInteresControllerTest {

    private MockMvc mockMvcCuentaIntervinientes;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectwrittWriter = objectMapper.writer();

    @Mock
    private TasaInteresRepository tasaInteresRepository;

    @InjectMocks
    private TasaInteresController tasaInteresController;

    TasaInteres REGISTRO_1 = new TasaInteres();

    @BeforeEach

    public void setUp() {
        MockitoAnnotations.initMocks(this);
        TasaInteresService tasaInteresService = new TasaInteresService(tasaInteresRepository);
        tasaInteresController = new TasaInteresController(tasaInteresService);
        this.mockMvcCuentaIntervinientes = MockMvcBuilders.standaloneSetup(tasaInteresController).build();
    }

    @Test
    public void obtenerTasaInteres_success() throws Exception {
        mockMvcCuentaIntervinientes.perform(MockMvcRequestBuilders
                .get("/api/v1/tasaInteres/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

}
