package com.banquito.core.baking.cuenta.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.banquito.core.baking.cuenta.domain.CuentaIntervinientes;
import com.banquito.core.baking.cuenta.service.CuentaIntervinientesService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/cuentaintervinientes")
public class CuentaIntervinientesController {
    private final CuentaIntervinientesService cuentaIntervinientesService;

    public CuentaIntervinientesController(CuentaIntervinientesService cuentaIntervinientesService) {
        this.cuentaIntervinientesService = cuentaIntervinientesService;
    }

    @GetMapping("/cuentas/{cuentaid}/clientes/{clientepersonaid}")
    public ResponseEntity<CuentaIntervinientes> GetById(@PathVariable("cuentaid") Integer cuentaId,
            @PathVariable("clientepersonaid") String clientePersonaId) {
        log.info("Recibida solicitud para obtener la cuenta interveniente con ID de cuenta: {} y ID de cliente: {}",
                cuentaId, clientePersonaId);

        return this.cuentaIntervinientesService.getById(cuentaId, clientePersonaId)
                .map(register -> new ResponseEntity<>(register, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/cuentas/{cuentaid}")
    public ResponseEntity<Iterable<CuentaIntervinientes>> ObtenerPorCuenta(@PathVariable("cuentaid") Integer cuentaId) {
        log.info("Se encontraron cuentas intervenientes para la cuenta con ID: {}", cuentaId);
        Iterable<CuentaIntervinientes> cuentaIntervinientes = this.cuentaIntervinientesService.getByCuenta(cuentaId);

        if (cuentaIntervinientes != null) {
            log.info("Datos encontrados para la cuenta con código: {}", cuentaId);
            return new ResponseEntity<>(cuentaIntervinientes, HttpStatus.OK);
        } else {
            log.error("No se encontraron datos para la cuenta con código: {}", cuentaId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/clientes/{clientepersonaid}")
    public ResponseEntity<Iterable<CuentaIntervinientes>> ObtenerPorCliente(
            @PathVariable("clientepersonaid") String clientepersonaid) {
        log.info("Se encontraron cuentas intervenientes para el cliete con ID: {}", clientepersonaid);
        Iterable<CuentaIntervinientes> cuentaIntervinientes = this.cuentaIntervinientesService.getByCodCliente(clientepersonaid);

        if (cuentaIntervinientes != null) {
            log.info("Datos encontrados para el cliente con código: {}", clientepersonaid);
            return new ResponseEntity<>(cuentaIntervinientes, HttpStatus.OK);
        } else {
            log.error("No se encontraron datos para el cliente con código: {}", clientepersonaid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Void> Guardar(@RequestBody CuentaIntervinientes cuentaIntervinientes) {
        log.info("Se va a guardar cuenta interviniente: {}", cuentaIntervinientes);
        try {
            this.cuentaIntervinientesService.crear(cuentaIntervinientes);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al guardar cuenta interviniente", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/cuentas/{cuentaid}/clientes/{clienteid}")
    public ResponseEntity<Boolean> Borrar(@PathVariable("cuentaid") Integer cuentaId,
            @PathVariable("clienteid") String clieclientePersonaIdnteId) {
        cuentaIntervinientesService.borrar(cuentaId, clieclientePersonaIdnteId);
        log.info("Cuenta interveniente eliminada con exito. ID de cuenta: {}, ID de cliente/persona: {}", cuentaId,
                clieclientePersonaIdnteId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> actualizar(@RequestBody CuentaIntervinientes cuentaIntervinientes) {        
        log.info("Se va a actualizar la cuenta interviniente: {}", cuentaIntervinientes);
        try {
            this.cuentaIntervinientesService.actualizar(cuentaIntervinientes);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al actualizar el cuenta interviniente", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}