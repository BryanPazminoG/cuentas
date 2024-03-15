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

import com.banquito.core.baking.cuenta.controller.TipoCuentaController;
import com.banquito.core.baking.cuenta.dao.TipoCuentaRepository;
import com.banquito.core.baking.cuenta.domain.TipoCuenta;
import com.banquito.core.baking.cuenta.service.TipoCuentaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)

public class TipoCuentaControllerTest {

    private MockMvc mockMvcTipoCuenta;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectwrittWriter = objectMapper.writer();

    @Mock
    private TipoCuentaRepository tipoCuentaRepository;

    @InjectMocks
    private TipoCuentaController tipoCuentaController ;

    TipoCuenta REGISTRO_1 = new TipoCuenta();

    @BeforeEach

    public void setUp() {
        MockitoAnnotations.initMocks(this);
        TipoCuentaService tipoCuentaService = new TipoCuentaService(tipoCuentaRepository);
        tipoCuentaController = new TipoCuentaController(tipoCuentaService);
        this.mockMvcTipoCuenta = MockMvcBuilders.standaloneSetup(tipoCuentaController).build();
    }

    @Test
    public void obtenerTipoCuenta_success() throws Exception {
        mockMvcTipoCuenta.perform(MockMvcRequestBuilders
                .get("/api/v1/tiposCuentas1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

}
