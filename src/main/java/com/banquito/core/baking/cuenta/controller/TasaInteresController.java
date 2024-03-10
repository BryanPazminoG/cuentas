package com.banquito.core.baking.cuenta.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.baking.cuenta.service.TasaInteresService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @CrossOrigin(origins = {"http://localhost:4200", "http://34.173.161.134:4201", "http://34.176.205.203:4202",
//                         "http://34.176.102.118:4203", "http://34.176.137.180:4204"})

@RestController
@RequestMapping("/api/v1/tasas-interes")
public class TasaInteresController {

    private final TasaInteresService tasaInteresService;

    public TasaInteresController(TasaInteresService tasaInteresService) {
        this.tasaInteresService = tasaInteresService;
    }

    // @GetMapping("/tipoCuenta/{tipoCuenta}")
    // public ResponseEntity<List<TasaInteres>> buscarPorTipoCuenta(@PathVariable(name = "tipoCuenta") Integer tipoCuenta) {
    //     log.info("Obteniendo tasa de interes por tipo de cuenta: {}", tipoCuenta);
    //     try {
    //         return ResponseEntity.ok(this.tasaInteresService.obtenerPorTipoCuenta(tipoCuenta));
    //     } catch(RuntimeException rte) {
    //         log.error("Error al obtener tasa de interes por tipo de cuenta", rte);
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable(name = "id") Integer id) {
        log.info("Se va a eliminar la tasa de interes con ID: {}", id);
        try {
            this.tasaInteresService.Eliminar(id);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al eliminar la tasa de interes", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
