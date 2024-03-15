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
import com.banquito.core.baking.cuenta.controller.PagoInteresController;
import com.banquito.core.baking.cuenta.dao.CuentaIntervinientesRepository;
import com.banquito.core.baking.cuenta.dao.CuentaRepository;
import com.banquito.core.baking.cuenta.dao.PagoInteresRepository;
import com.banquito.core.baking.cuenta.dao.TasaInteresRepository;
import com.banquito.core.baking.cuenta.dao.TipoCuentaRepository;
import com.banquito.core.baking.cuenta.dao.TransaccionRepository;
import com.banquito.core.baking.cuenta.domain.Cuenta;
import com.banquito.core.baking.cuenta.domain.PagoInteres;
import com.banquito.core.baking.cuenta.dto.TipoCuentaDTO;
import com.banquito.core.baking.cuenta.service.CuentaIntervinientesService;
import com.banquito.core.baking.cuenta.service.CuentaService;
import com.banquito.core.baking.cuenta.service.PagoInteresService;
import com.banquito.core.baking.cuenta.service.TasaInteresService;
import com.banquito.core.baking.cuenta.service.TipoCuentaService;
import com.banquito.core.baking.cuenta.service.TransaccionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@ExtendWith(MockitoExtension.class)

public class PagoInteresControllerTest {

    private MockMvc mockMvcPagoInteres;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectwrittWriter = objectMapper.writer();

    @Mock
    private PagoInteresRepository pagoInteresRepository;
    private CuentaRepository cuentaRepository;
    private TipoCuentaRepository tipoCuentaRepository;
    private TasaInteresRepository tasaInteresRepository;
    private TransaccionRepository transaccionRepository;

    @InjectMocks
    private PagoInteresController pagoInteresController;

    PagoInteres REGISTRO_1 = new PagoInteres();

    @BeforeEach

    public void setUp() {
        MockitoAnnotations.initMocks(this);
        CuentaService cuentaService = new CuentaService(cuentaRepository);
        TipoCuentaService tipoCuentaService = new TipoCuentaService(tipoCuentaRepository);
        TasaInteresService tasaInteresService = new TasaInteresService(tasaInteresRepository);
        TransaccionService transaccionService = new TransaccionService(transaccionRepository, cuentaRepository);
        PagoInteresService pagoInteresService = new PagoInteresService(pagoInteresRepository, cuentaRepository, tipoCuentaRepository, tasaInteresRepository, transaccionRepository);
        
        pagoInteresController = new PagoInteresController(pagoInteresService);
        this.mockMvcPagoInteres = MockMvcBuilders.standaloneSetup(pagoInteresController).build();
    }

    @Test
    public void obtenerPagoInteres_success() throws Exception {
        mockMvcPagoInteres.perform(MockMvcRequestBuilders
                .get("/api/v1/pagosInteres/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

}
