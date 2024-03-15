package com.banquito.core.baking.cuenta.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.baking.cuenta.dto.PagoInteresDTO;
import com.banquito.core.baking.cuenta.service.PagoInteresService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin(origins = {"http://localhost:4200"})

@RestController
@RequestMapping("/api/v1/pagosInteres")
public class PagoInteresController {

    private final PagoInteresService pagoInteresService;

    public PagoInteresController(PagoInteresService pagoInteresService) {
        this.pagoInteresService = pagoInteresService;
    }

    @GetMapping("/{codPagoInteres}")
    public ResponseEntity<PagoInteresDTO> ObtenerPorId(@PathVariable(name = "codPagoInteres") Integer codPagoInteres) {
        try {
            log.info("Obteniendo pagos de interes por ID: {}", codPagoInteres);
            return ResponseEntity.ok(this.pagoInteresService.BuscarPorId(codPagoInteres));
        } catch(RuntimeException rte) {
            log.error("Error al obtener pagos de interes", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cuentas/{codCuenta}")
    public ResponseEntity<List<PagoInteresDTO>> ObtenerPorCuenta(@PathVariable(name = "codCuenta") Integer codCuenta) {
        try {
            log.info("Obteniendo pagos de interes por cuenta: {}", codCuenta);
            return ResponseEntity.ok(this.pagoInteresService.BuscarPorCuenta(codCuenta));
        } catch(RuntimeException rte) {
            log.error("Error al obtener pagos de interes por cuenta", rte);
            return ResponseEntity.notFound().build();
        }
    }
}
