package com.banquito.core.baking.cuenta.controller;
import java.util.List;

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

import com.banquito.core.baking.cuenta.dto.CuentaDTO;
import com.banquito.core.baking.cuenta.service.CreacionException;
import com.banquito.core.baking.cuenta.service.CuentaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = {"http://localhost:4200", "http://34.173.161.134:4201", "http://34.176.205.203:4202", 
                        "http://34.176.102.118:4203", "http://34.176.137.180:4204"})
//@CrossOrigin(origins = "", allowedHeaders = "", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RestController
@RequestMapping("/api/v1/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public ResponseEntity<List<CuentaDTO>> listarCuentas() {
        log.info("Obteniendo listado de cuentas");
        return ResponseEntity.ok(this.cuentaService.listarTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> buscarPorId(@PathVariable(name = "id") Integer id) {
        log.info("Obteniendo cuenta con ID: {}", id);
        try {
            return ResponseEntity.ok(this.cuentaService.obtenerPorId(id));
        } catch(RuntimeException rte) {
            log.error("Error al obtener cuenta por ID", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Integer> crear(@RequestBody CuentaDTO cuenta) {
        log.info("Se va a crear la cuenta: {}", cuenta);
        try {
            Integer codCuenta = this.cuentaService.crear(cuenta);
            return ResponseEntity.ok(codCuenta);
        } catch(RuntimeException rte) {
            log.error("Error al crear la cuenta", rte);
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping
    public ResponseEntity<Void> actualizar(@RequestBody CuentaDTO cuenta) {
        log.info("Se va a actualizar la cuenta: {}", cuenta);
        try {
            this.cuentaService.actualizar(cuenta);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al actualizar la cuenta", rte);
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable(name = "id") Integer id) {
        log.info("Se va a eliminar la cuenta con ID: {}", id);
        try {
            this.cuentaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al eliminar la cuenta", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/numero/{numCuenta}")
    public ResponseEntity<CuentaDTO> buscarPorNumeroCuenta(@PathVariable(name = "numCuenta") String numCuenta) {
        log.info("Obteniendo cuenta: {}", numCuenta);
        try {
            return ResponseEntity.ok(this.cuentaService.obtenerPorNumeroCuenta(numCuenta));
        } catch(RuntimeException rte) {
            log.error("Error al obtener cuenta", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/balances")
    public ResponseEntity<Void> actualizarBalance(@RequestBody CuentaDTO cuenta) {
        log.info("Se va a actualizar el balance de la cuenta: {}", cuenta);
        try {
            this.cuentaService.actualizarBalance(cuenta);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al actualizar el balance de la cuenta", rte);
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/clientes/{codCliente}")
    public ResponseEntity<List<CuentaDTO>> obtenerCuentasCliente(@PathVariable(name = "codCliente") String codCliente) {
        log.info("Se va a obtener las cuentas de clientes: {}", codCliente);
        try {
            List<CuentaDTO> cuentasDTO = this.cuentaService.obtenerCuentasCliente(codCliente);
            return ResponseEntity.ok(cuentasDTO);
        } catch(CreacionException ce) {
            log.error("Error al obtener la cuenta del cliente", ce);
            return ResponseEntity.badRequest().build();
        }
    }



    
}
