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


import com.banquito.core.baking.cuenta.controller.TarjetaController;
import com.banquito.core.baking.cuenta.dao.TarjetaRepository;
import com.banquito.core.baking.cuenta.domain.Tarjeta;
import com.banquito.core.baking.cuenta.service.TarjetaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)

public class TarjetaControllerTest {

    private MockMvc mockMvcTarjeta;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectwrittWriter = objectMapper.writer();

    @Mock
    private TarjetaRepository tarjetaRepository;

    @InjectMocks
    private TarjetaController tarjetaController;

    Tarjeta REGISTRO_1 = new Tarjeta();

    @BeforeEach

    public void setUp() {
        MockitoAnnotations.initMocks(this);
        TarjetaService tarjetaService = new TarjetaService(tarjetaRepository);
        tarjetaController = new TarjetaController(tarjetaService);
        this.mockMvcTarjeta = MockMvcBuilders.standaloneSetup(tarjetaController).build();
    }

    @Test
    public void obtenerTarjetas_success() throws Exception {
        mockMvcTarjeta.perform(MockMvcRequestBuilders
                .get("/api/v1/tarjetas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

}
