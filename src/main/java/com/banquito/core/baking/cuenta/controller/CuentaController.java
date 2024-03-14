package com.banquito.core.baking.cuenta.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.baking.cuenta.dto.CuentaDTO;
import com.banquito.core.baking.cuenta.service.CuentaService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin(origins = {"http://localhost:4200"})
//@CrossOrigin(origins = "", allowedHeaders = "", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RestController
@RequestMapping("/api/v1/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping("/{codCuenta}")
    public ResponseEntity<CuentaDTO> ObtenerPorId(@PathVariable(name = "codCuenta") Integer codCuenta) {
        try {
            log.info("Obteniendo cuenta con ID: {}", codCuenta);
            return ResponseEntity.ok(this.cuentaService.BuscarPorId(codCuenta));
        } catch(RuntimeException rte) {
            log.error("Error al obtener cuenta por ID", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/numeroCuenta/{numeroCuenta}")
    public ResponseEntity<CuentaDTO> BuscarPorNumeroCuenta(@PathVariable(name = "numeroCuenta") String numeroCuenta) {
        try {
            log.info("Obteniendo cuenta por el numero de cuenta: {}", numeroCuenta);
            return ResponseEntity.ok(this.cuentaService.BuscarPorNumeroCuenta(numeroCuenta));
        } catch(RuntimeException rte) {
            log.error("Error al obtener cuenta", rte);
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/clientes/{codCliente}")
    public ResponseEntity<List<CuentaDTO>> ObtenerCuentasCliente(@PathVariable(name = "codCliente") String codCliente) {
        try {
            log.info("Obteniendo las cuentas del cliente: {}", codCliente);
            List<CuentaDTO> cuentasDTO = this.cuentaService.BuscarPorCliente(codCliente);
            return ResponseEntity.ok(cuentasDTO);
        } catch(RuntimeException rte) {
            log.error("Error al obtener la cuenta del cliente", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<CuentaDTO> Crear(@RequestBody CuentaDTO cuenta) {
        try {
            log.info("Creando la cuenta: {}", cuenta);
            CuentaDTO dto = this.cuentaService.Crear(cuenta);
            return ResponseEntity.ok(dto);
        } catch(RuntimeException rte) {
            log.error("Error al crear la cuenta", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> Actualizar(@RequestBody CuentaDTO cuenta) {
        try {
            log.info("Actualizando la cuenta: {}", cuenta);
            this.cuentaService.Actualizar(cuenta);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al actualizar la cuenta", rte);
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/balances")
    public ResponseEntity<Void> ActualizarBalance(@RequestBody CuentaDTO cuenta) {
        try {
            log.info("Actualizando el balance de la cuenta: {}", cuenta);
            this.cuentaService.ActualizarBalance(cuenta);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al actualizar el balance de la cuenta", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
